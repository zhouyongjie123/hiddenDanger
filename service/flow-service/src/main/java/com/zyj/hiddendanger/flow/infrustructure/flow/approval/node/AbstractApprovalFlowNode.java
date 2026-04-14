package com.zyj.hiddendanger.flow.infrustructure.flow.approval.node;

import com.zyj.hiddendanger.model.service.flow.infrustructure.AbstractFlowNode;
import com.zyj.hiddendanger.flow.infrustructure.flow.approval.enums.ApprovalStatusEnum;

/**
 * 抽象审批节点
 */
public abstract class AbstractApprovalFlowNode extends AbstractFlowNode implements ApprovalFlowNode {
    /**
     * 审批人id
     */
    protected String approverId;

    /**
     * 审批状态
     */
    protected ApprovalStatusEnum status;

    public AbstractApprovalFlowNode(String id, Double x, Double y) {
        super(id, x, y);
    }
}
