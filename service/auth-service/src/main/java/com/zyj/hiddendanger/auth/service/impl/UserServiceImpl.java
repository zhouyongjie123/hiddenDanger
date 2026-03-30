package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.UnknownExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        // 先保证唯一account
        ThrowUtil.throwIfTrue(
                isAccountExist(userRegisterDTO.getAccount()),
                () -> new AuthException(AuthExceptionCode.ACCOUNT_DUPLICATE));
        User user = new User().setAccount(userRegisterDTO.getAccount())
                              .setPassword(userRegisterDTO.getPassword())
                              .setRealName(userRegisterDTO.getRealName())
                              .setPhoneNumber(userRegisterDTO.getPhoneNumber())
                              .setDepartmentId(userRegisterDTO.getDepartmentId())
                              .setStatus(User.UserStatus.NORMAL)
                              .setRoleId(userRegisterDTO.getRoleId());
        ThrowUtil.throwIf(
                userMapper.insert(user) != 1, () -> new SystemException(UnknownExceptionCode.DATABASE_INSERT_ERROR));
        // 返回用户信息
        String deptName = departmentNameCache.get(user.getDepartmentId());
        if (deptName == null) {
            Department department = departmentMapper.selectById(user.getDepartmentId());
            deptName = department.getDepartmentName();
            departmentNameCache.put(department.getId(), department.getDepartmentName());
        }
        String roleName = roleMapper.selectById(user.getRoleId()).getRoleName().name();
        return user.toUserInfoVO(deptName, roleName);
    }

    @Override
    public Boolean isAccountExist(String account) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                                            .eq(User::getAccount, account)
        ) != null;
    }
}
