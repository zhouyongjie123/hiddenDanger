package com.zyj.hiddendanger.flow.infrustructure.approval.node;

import com.zyj.hiddendanger.flow.infrustructure.AbstractFlowNode;
import com.zyj.hiddendanger.flow.infrustructure.approval.enums.ApprovalStatusEnum;

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
