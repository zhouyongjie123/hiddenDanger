package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentPageQueryDTO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/page")
    public PageResponseResult<DepartmentInfoVO> page(@RequestBody DepartmentPageQueryDTO departmentPageQueryDTO) {
        return PageResponseResult.ok(departmentService.page(departmentPageQueryDTO));
    }

    @GetMapping("/select")
    public ResponseResult<List<DepartmentSelectionVO>> select() {
        return ResponseResult.ok(departmentService.getSelectionVo());
    }

    @GetMapping("/select/leader")
    public ResponseResult<List<UserSelectionVO>> selectLeader() {
        return ResponseResult.ok(departmentService.getLeaderSelectionVO());
    }
}
