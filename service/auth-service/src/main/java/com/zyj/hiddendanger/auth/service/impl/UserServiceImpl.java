package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.vo.UserLoginVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;

    private final DepartmentMapper departmentMapper;

    @Resource
    private Cache<String, String> departmentNameCache;

    @Override
    public UserLoginVO getUserLoginVO(String account, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                                                 .eq(User::getAccount, account)
        );
        // 如果这个用户不存在，则抛出异常
        ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ACCOUNT_ERROR));
        // 判断密码是否正确
        ThrowUtil.throwIf(
                !user.getPassword().equals(password), () -> new AuthException(AuthExceptionCode.PASSWORD_ERROR));
        String deptName = departmentNameCache.get(user.getDepartmentId());
        if (deptName == null){
            Department department = departmentMapper.selectById(user.getDepartmentId());
            deptName = department.getDepartmentName();
            departmentNameCache.put(department.getId(), department.getDepartmentName());
        }
        return user.toUserLoginVO().setDepartmentName(deptName).setRoleName("ADMIN");
    }
}
