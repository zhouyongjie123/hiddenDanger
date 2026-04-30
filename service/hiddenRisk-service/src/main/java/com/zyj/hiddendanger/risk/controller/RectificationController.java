package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureApprovalProcessVO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 整改控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rectify")
public class RectificationController {
    private final RectificationMeasureService rectificationMeasureService;

    /**
     * 上报整改措施
     */
    @PostMapping("/report")
    public ResponseResult<?> report(@RequestBody @Valid RectificationMeasureReportDTO rectificationMeasureReportDTO) {
        rectificationMeasureService.report(rectificationMeasureReportDTO);
        return ResponseResult.ok("提交成功");
    }

    /**
     * 获取整改信息
     */
    @GetMapping("/risk")
    public ResponseResult<RectificationMeasureVO> getRectificationMeasure(@RequestParam("riskId") String riskId) {
        return ResponseResult.ok(rectificationMeasureService.getRectificationMeasureByHiddenRiskId(riskId));
    }

    /**
     * 获取整改措施审批图
     */
    @GetMapping("/show/flow")
    public ResponseResult<RectificationMeasureApprovalProcessVO> showFlow(
            @RequestParam("riskId") String riskId) {
        return ResponseResult.ok(
                rectificationMeasureService.getRectificationMeasureApprovalProcessVOByHiddenRiskId(riskId));
    }
}
