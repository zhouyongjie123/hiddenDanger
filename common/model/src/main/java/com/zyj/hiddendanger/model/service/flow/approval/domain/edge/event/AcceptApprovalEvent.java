package com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.event.ApprovalFlowNodeStatusEventEnum;

public class AcceptApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public AcceptApprovalEvent(String businessId, String eventId, String approvalMessage) {
        super(businessId, eventId, approvalMessage);
    }

    public ApprovalFlowNodeStatusEventEnum getApprovalFlowNodeStatusEventEnum() {
        return ApprovalFlowNodeStatusEventEnum.ACCEPT;
    }
}
