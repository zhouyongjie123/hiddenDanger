package com.zyj.hiddendanger.flow.infrustructure.flow.approval.edge;

import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdge;
import lombok.Data;

/**
 * 审批流程边
 */
@Data
public abstract class AbstractApprovalFlowEdge<ApprovalFlowEdgeEventEnum> implements FlowEdge<ApprovalFlowEdgeEventEnum> {
    protected String id;

    protected String sourceNodeId;

    protected String targetNodeId;

    protected ApprovalFlowEdgeEventEnum event;
}
