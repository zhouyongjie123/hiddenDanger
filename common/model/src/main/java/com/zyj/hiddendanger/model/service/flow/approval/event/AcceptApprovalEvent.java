package com.zyj.hiddendanger.model.service.flow.approval.event;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;

public class AcceptApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public AcceptApprovalEvent(ApprovalFlowNode sourceNode, String businessId, String eventId, String approvalMessage) {
        super(sourceNode, businessId, eventId, approvalMessage);
    }
}
