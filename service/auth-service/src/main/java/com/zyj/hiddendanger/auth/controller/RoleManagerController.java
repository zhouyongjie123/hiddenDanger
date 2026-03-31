package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add/{roleName}")
    public ResponseResult<RoleSelectionVO> add(@PathVariable String roleName) {
        RoleSelectionVO roleSelectionVO = roleService.addRole(roleName);
        return ResponseResult.ok(roleSelectionVO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult<?> delete(@PathVariable String id) {
        roleService.deleteById(id);
        return ResponseResult.ok("删除成功");
    }
}
