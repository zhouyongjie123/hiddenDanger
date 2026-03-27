package com.zyj.hiddendanger.risk.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.zyj.hiddendanger.risk.infrustructure.chain.LoginHandlerComposite;
import com.zyj.hiddendanger.risk.mapper.UserMapper;
import com.zyj.hiddendanger.risk.service.SessionService;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;
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
        UserLoginVO userLoginVO = loginHandlerComposite.handle(loginRequestDTO);
        StpUtil.login(userLoginVO.getId());
        return userLoginVO;
    }

    @Override
    public void logout() {
        StpUtil.logout(UserIdContextHolder.get());
    }
}
