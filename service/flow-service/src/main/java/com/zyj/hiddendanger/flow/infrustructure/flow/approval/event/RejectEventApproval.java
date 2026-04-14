package com.zyj.hiddendanger.flow.infrustructure.flow.approval.event;

import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowNode;

public class RejectEventApproval extends AbstractApprovalFlowEdgeEvent {
    public RejectEventApproval(FlowNode sourceNode, String businessId, String eventId, String approvalMessage) {
        super(sourceNode, businessId, eventId, approvalMessage);
    }
}
