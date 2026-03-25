package com.zyj.hiddendanger.risk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.vo.HiddenRiskVO;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/risk")
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    @GetMapping("/page")
    public ResponseResult<Page<HiddenRiskVO>> page(
            @PathParam("current") Long current, @PathParam("pageSize") Long pageSize) {
        return ResponseResult.ok(hiddenRiskService.page(new Page<>(current, pageSize)));
    }
}
