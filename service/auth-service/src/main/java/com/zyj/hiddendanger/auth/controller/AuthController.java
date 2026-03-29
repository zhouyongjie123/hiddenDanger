package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.SessionService;
import com.zyj.hiddendanger.model.service.auth.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserLoginVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/auth")
public class AuthController {
    private final SessionService sessionService;

    @PostMapping("/login")
    public ResponseResult<UserLoginVO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseResult.ok(sessionService.login(loginRequestDTO));
    }

    @DeleteMapping("/logout")
    public ResponseResult<?> logout() {
        sessionService.logout();
        return ResponseResult.ok("注销成功");
    }
}
