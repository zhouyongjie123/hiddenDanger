package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.SessionService;
import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final SessionService sessionService;

    @PostMapping("/login")
    public ResponseResult<UserLoginVO> login(LoginRequestDTO loginRequestDTO) {
        return ResponseResult.ok(sessionService.login(loginRequestDTO));
    }
}
