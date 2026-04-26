package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.MyRectificationMeasurePageQueryDTO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flow")
public class FlowController {
    private final RectificationMeasureService rectificationMeasureService;

    /**
     * 获取我要审批的整改措施
     */
    @PostMapping("/page")
    public PageResponseResult<RectificationMeasureVO> getMyRectificationMeasure(
            @RequestBody
            MyRectificationMeasurePageQueryDTO dto) {
        return PageResponseResult.ok(rectificationMeasureService.getMyRectificationMeasurePageVO(dto));
    }

}
