package com.zyj.hiddendanger.auth.facade;

import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Facade
@Service
@DubboService
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements UserFacadeService {
    private final UserService userService;

    @Override
    public String getRealNameById(String userId) {
        User user = userService.getById(userId);
        ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
        return user.getRealName();
    }
}
