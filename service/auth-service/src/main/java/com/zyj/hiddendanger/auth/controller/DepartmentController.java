package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DepartmentController {
    @GetMapping("/all")
    public ResponseResult<Object> all() {
        return ResponseResult.ok("获取部门列表成功");
    }
}
