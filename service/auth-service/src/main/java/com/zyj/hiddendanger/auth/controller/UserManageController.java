package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserLoginVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserManageController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseResult<UserInfoVO> register(@RequestBody @Validated UserRegisterDTO userRegisterDTO) {
        return ResponseResult.ok(userService.register(userRegisterDTO));
    }
}
