package com.zyj.hiddendanger.auth.service.impl;

import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.SessionService;
import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final UserMapper userMapper;

    @Override
    public UserLoginVO login(LoginRequestDTO loginRequestDTO) {
        // todo 这里用责任链实现不同方式登录
        return null;
    }
}
