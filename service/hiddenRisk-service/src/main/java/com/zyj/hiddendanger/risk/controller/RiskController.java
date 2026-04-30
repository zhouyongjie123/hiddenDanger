package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskReportDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.model.service.risk.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    /**
     * 查询隐患记录
     */
    @PostMapping("/page")
    public PageResponseResult<HiddenRiskVO> page(@RequestBody HiddenRiskPageQueryDTO hiddenRiskPageQueryDTO) {
        return PageResponseResult.ok(hiddenRiskService.page(hiddenRiskPageQueryDTO));
    }

    /**
     * 上报隐患
     */
    @PostMapping("/report")
    public ResponseResult<HiddenRiskVO> report(@RequestBody HiddenRiskReportDTO hiddenRiskReportDTO) {
        return ResponseResult.ok(hiddenRiskService.report(hiddenRiskReportDTO));
    }

    /**
     * 整改隐患
     */
    @PutMapping("/rectify")
    public ResponseResult<?> rectify(@RequestParam("riskId") String riskId) {
        hiddenRiskService.rectify(riskId);
        return ResponseResult.ok("状态推进成功");
    }
}
