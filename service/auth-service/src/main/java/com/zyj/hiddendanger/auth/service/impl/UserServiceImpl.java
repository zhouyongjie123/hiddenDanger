package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.DatabaseExceptionCode;
import com.zyj.hiddendanger.model.service.auth.dto.UserPageQueryDTO;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.web.util.PageUtil;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserUpdateDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;

    private final DepartmentMapper departmentMapper;

    private final RoleMapper roleMapper;


    @Resource
    private Cache<String, String> departmentNameCache;

    @Resource
    private Cache<String, String> roleNameCache;

    @Override
    public UserInfoDTO getUserInfoByAccount(String account) {
        UserInfoDTO userInfoDTO = userMapper.getUserInfoDTOByAccount(account);
        ThrowUtil.throwIfNull(userInfoDTO, () -> new AuthException(AuthExceptionCode.ACCOUNT_ERROR));
        return userInfoDTO;
    }

    @Override
    public void deleteUserById(String id) {
        ThrowUtil.throwIf(userMapper.deleteById(id) != 1, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
    }

    @Override
    public UserInfoVO register(UserRegisterDTO userRegisterDTO) {
        User user = new User().setAccount(userRegisterDTO.getAccount())
                              .setPassword(userRegisterDTO.getPassword())
                              .setRealName(userRegisterDTO.getRealName())
                              .setPhoneNumber(userRegisterDTO.getPhoneNumber())
                              .setDepartmentId(userRegisterDTO.getDepartmentId())
                              .setStatus(User.UserStatus.NORMAL)
                              .setAvatarUrl(userRegisterDTO.getAvatarUrl())
                              .setRoleId(userRegisterDTO.getRoleId());
        // 执行插入操作,如果account存在则抛出异常
        ThrowUtil.supplyWithExceptionTranslation(
                () -> userMapper.insert(user), DuplicateKeyException.class,
                (duplicateKeyException) -> new AuthException(
                        AuthExceptionCode.ACCOUNT_DUPLICATE));
        // 返回用户信息
        return userMapper.getUserInfoDTOById(user.getId()).toUserInfoVO();
    }

    @Override
    public Page<UserInfoVO> page(UserPageQueryDTO userPageQueryDTO) {
        Page<User> page = userMapper.selectPage(
                Page.of(userPageQueryDTO.getCurrent(), userPageQueryDTO.getPageSize()),
                new LambdaQueryWrapper<User>()
                        .eq(
                                userPageQueryDTO.getDepartmentId() != null && !userPageQueryDTO
                                        .getDepartmentId()
                                        .isBlank(),
                                User::getDepartmentId, userPageQueryDTO.getDepartmentId())
                        .eq(
                                userPageQueryDTO.getRoleId() != null && !userPageQueryDTO.getRoleId().isBlank(),
                                User::getRoleId,
                                userPageQueryDTO.getRoleId())
        );

        List<UserInfoVO> results = page.getRecords().stream().map(record -> {
            String departmentName = departmentNameCache.get(record.getDepartmentId());
            if (!StringUtils.hasText(departmentName)) {
                departmentName = departmentMapper.selectById(record.getDepartmentId()).getDepartmentName();
                departmentNameCache.put(record.getDepartmentId(), departmentName);
            }
            String roleName = roleNameCache.get(record.getRoleId());
            if (!StringUtils.hasText(roleName)) {
                roleName = roleMapper.selectById(record.getRoleId()).getRoleName();
                roleNameCache.put(record.getRoleId(), roleName);
            }
            return record.toUserInfoVO(departmentName, roleName);
        }).toList();
        return PageUtil.convert2Page(page, results);
    }

    @Override
    public Boolean isAccountExist(String account) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                                            .eq(User::getAccount, account)
        ) != null;
    }

    @Override
    public List<UserSelectionVO> getUserInfosByDepartmentId(String departmentId) {
        return userMapper.selectList(new LambdaQueryWrapper<User>()
                                             .eq(User::getDepartmentId, departmentId)).stream().map(
                User::toUserSelectionVO).toList();
    }

    @Override
    public List<UserSelectionVO> selectUserByRoleId(String roleId) {
        return userMapper.getUserInfoByRoleId(roleId).stream().map(UserInfoDTO::toUserSelectionVO).toList();
    }

    @Override
    public UserInfoVO updateUser(UserUpdateDTO userUpdateDTO) {
        ThrowUtil.throwIf(
                userMapper.update(new LambdaUpdateWrapper<User>()
                                          .eq(User::getId, userUpdateDTO.getId())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getAccount()),
                                                  User::getAccount,
                                                  userUpdateDTO.getAccount())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getPassword()),
                                                  User::getPassword,
                                                  userUpdateDTO.getPassword())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getRealName()),
                                                  User::getRealName,
                                                  userUpdateDTO.getRealName())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getPhoneNumber()),
                                                  User::getPhoneNumber,
                                                  userUpdateDTO.getPhoneNumber())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getDepartmentId()),
                                                  User::getDepartmentId,
                                                  userUpdateDTO.getDepartmentId())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getRoleId()), User::getRoleId,
                                                  userUpdateDTO.getRoleId())
                                          .set(
                                                  StringUtils.hasText(userUpdateDTO.getAvatarUrl()),
                                                  User::getAvatarUrl,
                                                  userUpdateDTO.getAvatarUrl())) != 1,
                () -> new SystemException(DatabaseExceptionCode.UPDATE_ERROR));
        return userMapper.getUserInfoDTOById(userUpdateDTO.getId()).toUserInfoVO();
    }
}
