package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserManageController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseResult<UserInfoVO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return ResponseResult.ok(userService.register(userRegisterDTO));
    }

    @GetMapping("/page")
    public PageResponseResult<UserInfoVO> page(
            @RequestParam(required = false) String current, @RequestParam(required = false) String pageSize){
//        userService.
        return null;
    }
}
