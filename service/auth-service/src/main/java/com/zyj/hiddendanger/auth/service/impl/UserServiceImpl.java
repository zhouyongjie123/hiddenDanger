package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.infrustructure.dto.UserPageQueryDTO;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.database.util.PageUtil;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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

    @Override
    public UserInfoDTO getUserInfoByAccount(String account) {
        UserInfoDTO userInfoDTO = userMapper.getUserInfoByAccount(account);
        ThrowUtil.throwIfNull(userInfoDTO, () -> new AuthException(AuthExceptionCode.ACCOUNT_ERROR));
        return userInfoDTO;
    }

    @Override
    public UserInfoVO register(UserRegisterDTO userRegisterDTO) {
        // todo 增加管理员的权限校验
        User user = new User().setAccount(userRegisterDTO.getAccount())
                              .setPassword(userRegisterDTO.getPassword())
                              .setRealName(userRegisterDTO.getRealName())
                              .setPhoneNumber(userRegisterDTO.getPhoneNumber())
                              .setDepartmentId(userRegisterDTO.getDepartmentId())
                              .setStatus(User.UserStatus.NORMAL)
                              .setRoleId(userRegisterDTO.getRoleId());
        // 执行插入操作,如果account存在则抛出异常
        ThrowUtil.supplyWithExceptionTranslation(
                () -> userMapper.insert(user), DuplicateKeyException.class,
                (duplicateKeyException) -> new AuthException(
                        AuthExceptionCode.ACCOUNT_DUPLICATE));
        // 返回用户信息
        return userMapper.getUserInfoById(user.getId()).toUserInfoVO();
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
            return record.toUserInfoVO("null", "null");
        }).toList();
        return PageUtil.pageConvert(page, results);
    }

    @Override
    public Boolean isAccountExist(String account) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                                            .eq(User::getAccount, account)
        ) != null;
    }
}
