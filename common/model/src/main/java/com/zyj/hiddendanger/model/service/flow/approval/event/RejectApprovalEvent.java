package com.zyj.hiddendanger.model.service.flow.approval.event;

public class RejectApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public RejectApprovalEvent(String sourceNodeId, String businessId, String eventId, String approvalMessage) {
        super(sourceNodeId, businessId, eventId, approvalMessage);
    }
}
