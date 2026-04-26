package com.zyj.hiddendanger.model.service.flow.approval.domain.node.status;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.RejectApprovalEvent;

public enum ApprovalStatusEnum {
    // 待审批
    PENDING,
    // 审批中
    PROCESSING,
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
