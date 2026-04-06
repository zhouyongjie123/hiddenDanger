package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.model.service.auth.dto.UserPageQueryDTO;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserManageController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseResult<UserInfoVO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return ResponseResult.ok(userService.register(userRegisterDTO));
    }

    @PostMapping("/page")
    public PageResponseResult<UserInfoVO> page(@RequestBody UserPageQueryDTO userPageQueryDTO) {
        return PageResponseResult.ok(userService.page(userPageQueryDTO));
    }

    @GetMapping("/select")
    public ResponseResult<List<UserSelectionVO>> getUserInfoByDepartment(
            @RequestParam("departmentId") String departmentId) {
        return ResponseResult.ok(userService.getUserInfosByDepartmentId(departmentId));
    }

    @DeleteMapping("/delete")
    public ResponseResult<?> delete(@RequestParam("id") Long id) {
        userService.removeById(id);
        return ResponseResult.ok("删除成功");
    }
    @GetMapping("/select/role")
    public ResponseResult<List<UserSelectionVO>> selectUserByRole(@RequestParam("roleId") String roleId) {
        return ResponseResult.ok(userService.selectUserByRoleId(roleId));
    }
}
