package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flow")
public class FlowController {
    private final RectificationMeasureService rectificationMeasureService;

    /**
     * 获取我要审批的整改措施
     */
    @GetMapping("/list")
    public ResponseResult<List<RectificationMeasureVO>> getMyRectificationMeasure() {
        rectificationMeasureService.getMyRectificationMeasureList();
        return ResponseResult.ok(List.of());
    }

}
