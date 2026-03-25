package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/risk")
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    @GetMapping("/all")
    public ResponseResult
}
