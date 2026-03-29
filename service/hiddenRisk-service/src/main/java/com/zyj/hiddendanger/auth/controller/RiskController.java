package com.zyj.hiddendanger.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import com.zyj.hiddendanger.auth.service.HiddenRiskService;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/risk")
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    @GetMapping("/page")
    public PageResponseResult<HiddenRiskVO> page(
            @RequestParam("current") Long current, @RequestParam("pageSize") Long pageSize) {
        return PageResponseResult.ok(hiddenRiskService.page(new Page<>(current, pageSize)));
    }
}
