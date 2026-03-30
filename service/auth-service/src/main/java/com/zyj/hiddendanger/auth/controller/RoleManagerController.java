package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleManagerController {
    private final RoleService roleService;

    @GetMapping("/select")
    public ResponseResult<List<RoleSelectionVO>> select() {
        return ResponseResult.ok(roleService.getSelectionVO());
    }
}
