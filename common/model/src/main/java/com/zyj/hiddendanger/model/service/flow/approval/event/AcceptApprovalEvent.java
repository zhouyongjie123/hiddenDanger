package com.zyj.hiddendanger.model.service.flow.approval.event;

public class AcceptApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public AcceptApprovalEvent(String sourceNodeId, String businessId, String eventId, String approvalMessage) {
        super(sourceNodeId, businessId, eventId, approvalMessage);
    }
}
