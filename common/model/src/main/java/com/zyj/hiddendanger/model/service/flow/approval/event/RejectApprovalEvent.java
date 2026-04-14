package com.zyj.hiddendanger.model.service.flow.approval.event;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;

public class RejectApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public RejectApprovalEvent(ApprovalFlowNode sourceNode, String businessId, String eventId, String approvalMessage) {
        super(sourceNode, businessId, eventId, approvalMessage);
    }
}
