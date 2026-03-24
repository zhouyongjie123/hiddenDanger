package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.exception.auth.AuthException;
import com.zyj.hiddendanger.model.exception.auth.AuthExceptionCodeEnum;
import com.zyj.hiddendanger.model.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserLoginVO getUserLoginVO(String account, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                                                 .eq(User::getAccount, account)
        );
        // 如果这个用户不存在，则抛出异常
        ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCodeEnum.AccountError));
        // 判断密码是否正确
        ThrowUtil.throwIf(!user.getPassword().equals(password), () -> new AuthException(AuthExceptionCodeEnum.PasswordError));
        return user.toUserLoginVO().setDepartmentName("没有部门").setRoleName("ADMIN");
    }
}




