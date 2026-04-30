package com.zyj.hiddendanger.auth.facade;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Facade
@Service
@DubboService
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements UserFacadeService {
    private final UserService userService;

    private final UserMapper userMapper;

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

    @Override
    public Map<String, String> getRealNameByIds(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Map.of();
        }
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getId, userIds)
                                                         .select(User::getId, User::getRealName));
        return users.stream().collect(Collectors.toMap(User::getId, User::getRealName));
    }
}
