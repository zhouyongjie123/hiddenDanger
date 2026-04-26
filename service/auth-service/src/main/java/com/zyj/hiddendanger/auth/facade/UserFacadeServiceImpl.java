package com.zyj.hiddendanger.auth.facade;

import com.alicp.jetcache.Cache;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Facade
@Service
@DubboService
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements UserFacadeService {
    private final UserService userService;

    @Resource
    private Cache<String, String> userNameCache;

    @Override
    public String getRealNameById(String userId) {
        String realName = userNameCache.get(userId);
        if (realName != null) {
            return realName;
        }
        User user = userService.getById(userId);
        ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
        userNameCache.put(userId, user.getRealName());
        return user.getRealName();
    }
}
