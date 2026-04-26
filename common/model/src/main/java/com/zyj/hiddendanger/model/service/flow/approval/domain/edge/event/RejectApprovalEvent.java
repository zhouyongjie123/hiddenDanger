package com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.event.ApprovalFlowNodeStatusEventEnum;

public class RejectApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public RejectApprovalEvent(String businessId, String eventId, String approvalMessage) {
        super(businessId, eventId, approvalMessage);
    }

    @Override
    public ApprovalFlowNodeStatusEventEnum getApprovalFlowNodeStatusEventEnum() {
        return ApprovalFlowNodeStatusEventEnum.REJECT;
    }
}
