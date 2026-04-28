package com.zyj.hiddendanger.risk.service;

import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureApprovalDTO;

/**
 * 完成隐患的审批
 */
public interface RectificationMeasureApprovalService {
    /**
     * 审批通过
     */
    void approvalAccept(RectificationMeasureApprovalDTO dto);

    /**
     * 审批拒绝
     */
    void approvalReject(RectificationMeasureApprovalDTO dto);
}
