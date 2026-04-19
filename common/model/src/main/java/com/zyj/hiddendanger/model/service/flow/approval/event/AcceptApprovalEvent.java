package com.zyj.hiddendanger.model.service.flow.approval.event;

public class AcceptApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public AcceptApprovalEvent(String businessId, String eventId, String approvalMessage) {
        super(businessId, eventId, approvalMessage);
    }
}
