package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/page")
    public PageResponseResult<DepartmentInfoVO> page(
            @RequestParam("current") Long current, @RequestParam("pageSize") Long pageSize) {
        return PageResponseResult.ok(departmentService.page(current, pageSize));
    }

    @GetMapping("/select")
    public ResponseResult<List<DepartmentSelectionVO>> select() {
        return ResponseResult.ok(departmentService.getSelectionVo());
    }
}
