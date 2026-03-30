package com.zyj.hiddendanger.auth.infrustructure.chain.login;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.chain.PredicatableHandler;
import com.zyj.hiddendanger.model.service.auth.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AccountAndPasswordLoginHandler implements PredicatableHandler<UserInfoDTO, LoginRequestDTO> {
    @Resource
    private UserService userService;

    @Override
    public UserInfoDTO handle(LoginRequestDTO value) {
        return userService.getUserInfoByAccount(value.getAccount());
    }

    @Override
    public Boolean isSupported(LoginRequestDTO value) {
        return Stream.of(value.getAccount(), value.getPassword()).allMatch(StringUtils::isNotBlank);
    }
}
