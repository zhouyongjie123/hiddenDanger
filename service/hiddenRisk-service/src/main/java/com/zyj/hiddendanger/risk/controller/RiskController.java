package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskReportDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/risk")
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    @PostMapping("/page")
    public PageResponseResult<HiddenRiskVO> page(@RequestBody HiddenRiskPageQueryDTO hiddenRiskPageQueryDTO) {
        return PageResponseResult.ok(hiddenRiskService.page(hiddenRiskPageQueryDTO));
    }

    @PostMapping("/report")
    public ResponseResult<HiddenRiskVO> report(@RequestBody HiddenRiskReportDTO hiddenRiskReportDTO) {
        return ResponseResult.ok(hiddenRiskService.report(hiddenRiskReportDTO));
    }
}
