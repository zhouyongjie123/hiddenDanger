package com.zyj.hiddendanger.model.service.flow.approval.event;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractApprovalFlowEdgeEvent implements FlowEdgeEvent {
    protected ApprovalFlowNode sourceNode;

    protected String businessId;

    protected String eventId;

    // 审批意见
    protected String approvalMessage;
}
