package com.zyj.hiddendanger.flow.infrustructure.flow.approval.edge;

import com.zyj.hiddendanger.flow.infrustructure.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdge;
import lombok.Data;

/**
 * 审批流程边
 */
@Data
public abstract class AbstractApprovalFlowEdge implements FlowEdge<AbstractApprovalFlowEdgeEvent> {
    protected String id;

    protected String sourceNodeId;

    protected String targetNodeId;

    protected AbstractApprovalFlowEdgeEvent event;
}
