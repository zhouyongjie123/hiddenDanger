package com.zyj.hiddendanger.model.service.flow.approval.enums;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.RejectApprovalEvent;

public enum ApprovalStatusEnum {
    // 待审批
    PENDING,
    // 审批通过
    ACCEPTED,
    // 审批拒绝
    REJECTED;

    public static ApprovalStatusEnum of(AbstractApprovalFlowEdgeEvent event) {
        if (event instanceof AcceptApprovalEvent) {
            return ACCEPTED;
        } else if (event instanceof RejectApprovalEvent) {
            return REJECTED;
        }
        return PENDING;
    }
}
