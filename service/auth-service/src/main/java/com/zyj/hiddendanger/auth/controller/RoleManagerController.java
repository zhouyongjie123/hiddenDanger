package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.model.service.auth.dto.RoleAddDTO;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.validation.Valid;
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
    @GetMapping("/select/leader")
    public ResponseResult<List<RoleSelectionVO>> selectLeader() {
        return ResponseResult.ok(roleService.getLeaderSelectVO());
    }

    @PostMapping("/add")
    public ResponseResult<RoleSelectionVO> add(@RequestBody @Valid RoleAddDTO roleAddDTO) {
        RoleSelectionVO roleSelectionVO = roleService.addRole(roleAddDTO);
        return ResponseResult.ok(roleSelectionVO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult<?> delete(@PathVariable String id) {
        roleService.deleteById(id);
        return ResponseResult.ok("删除成功");
    }
}
