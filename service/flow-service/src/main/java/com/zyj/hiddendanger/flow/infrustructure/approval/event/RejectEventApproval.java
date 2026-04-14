package com.zyj.hiddendanger.flow.infrustructure.approval.event;

import com.zyj.hiddendanger.flow.infrustructure.FlowNode;

public class RejectEventApproval extends AbstractApprovalFlowEdgeEvent {
    public RejectEventApproval(FlowNode sourceNode, String businessId, String eventId, String approvalMessage) {
        super(sourceNode, businessId, eventId, approvalMessage);
    }
}
