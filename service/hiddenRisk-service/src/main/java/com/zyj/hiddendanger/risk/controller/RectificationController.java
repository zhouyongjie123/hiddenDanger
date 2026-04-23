package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
