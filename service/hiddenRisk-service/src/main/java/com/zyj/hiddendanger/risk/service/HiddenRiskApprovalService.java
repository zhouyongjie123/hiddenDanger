package com.zyj.hiddendanger.risk.service;

import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;

/**
 * 完成隐患的审批
 */
public interface HiddenRiskApprovalService {
    /**
     * 审批通过
     */
    void approvalAccept(HiddenRiskApprovalDTO dto);

    /**
     * 审批拒绝
     */
    void approvalReject(HiddenRiskApprovalDTO dto);
}
