package com.zyj.hiddendanger.risk.controller;

import com.zyj.hiddendanger.model.service.risk.dto.MyRectificationMeasurePageQueryDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureApprovalDTO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureApprovalService;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flow")
public class FlowController {
    private final RectificationMeasureService rectificationMeasureService;

    private final RectificationMeasureApprovalService rectificationMeasureApprovalService;

    /**
     * 获取我要审批的整改措施
     */
    @PostMapping("/page")
    public PageResponseResult<RectificationMeasureVO> getMyRectificationMeasure(
            @RequestBody
            MyRectificationMeasurePageQueryDTO dto) {
        return PageResponseResult.ok(rectificationMeasureService.getMyRectificationMeasurePageVO(dto));
    }

    /**
     * 审批通过
     */
    @PostMapping("/approve/accept")
    public ResponseResult<?> accept(@RequestBody RectificationMeasureApprovalDTO dto) {
        rectificationMeasureApprovalService.approvalAccept(dto);
        return ResponseResult.ok("提交成功");
    }

    /**
     * 审批拒绝
     */
    @PostMapping("/approve/reject")
    public ResponseResult<?> reject(@RequestBody RectificationMeasureApprovalDTO dto) {
        rectificationMeasureApprovalService.approvalReject(dto);
        return ResponseResult.ok("提交成功");
    }
}
