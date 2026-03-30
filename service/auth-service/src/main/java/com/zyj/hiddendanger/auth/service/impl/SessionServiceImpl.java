package com.zyj.hiddendanger.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.zyj.hiddendanger.auth.infrustructure.chain.LoginHandlerComposite;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.SessionService;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.service.auth.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final UserMapper userMapper;

    private final LoginHandlerComposite loginHandlerComposite;

    @Override
    public UserLoginVO login(LoginRequestDTO loginRequestDTO) {
        // 这里用责任链实现不同方式登录
        UserInfoDTO userInfoDTO = loginHandlerComposite.handle(loginRequestDTO);
        // 检验密码
        ThrowUtil.throwIfFalse(
                userInfoDTO.getPassword().equals(loginRequestDTO.getPassword()), () -> new AuthException(
                        AuthExceptionCode.PASSWORD_ERROR));
        // 实现登录
        StpUtil.login(userInfoDTO.getId());
        // 获取token信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return new UserLoginVO().setId(userInfoDTO.getId())
                                .setAccount(userInfoDTO.getAccount())
                                .setRealName(userInfoDTO.getRealName())
                                .setPhoneNumber(userInfoDTO.getPhoneNumber())
                                .setDepartmentName(userInfoDTO.getDepartmentName())
                                .setRoleName(userInfoDTO.getRoleName())
                                .setTokenName(tokenInfo.getTokenName())
                                .setTokenValue(tokenInfo.getTokenValue());
    }

    @Override
    public void logout() {
        StpUtil.logout(UserIdContextHolder.get());
    }
}
