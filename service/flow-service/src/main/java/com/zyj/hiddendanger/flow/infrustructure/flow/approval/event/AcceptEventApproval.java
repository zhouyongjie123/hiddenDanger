package com.zyj.hiddendanger.flow.infrustructure.flow.approval.event;

public class AcceptEventApproval extends AbstractApprovalFlowEdgeEvent {
    public AcceptEventApproval(FlowNode sourceNode, String businessId, String eventId, String approvalMessage) {
        super(sourceNode, businessId, eventId, approvalMessage);
    }
}
