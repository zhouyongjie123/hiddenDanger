package com.zyj.hiddendanger.model.service.flow.approval.event;

public class RejectApprovalEvent extends AbstractApprovalFlowEdgeEvent {
    public RejectApprovalEvent(String businessId, String eventId, String approvalMessage) {
        super(businessId, eventId, approvalMessage);
    }
}
