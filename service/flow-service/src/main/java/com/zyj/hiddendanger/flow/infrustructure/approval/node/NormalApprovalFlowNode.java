package com.zyj.hiddendanger.flow.infrustructure.approval.node;

import com.zyj.hiddendanger.flow.infrustructure.approval.enums.ApprovalStatusEnum;

/**
 * 正常审批节点
 */
public class NormalApprovalFlowNode extends AbstractApprovalFlowNode {
    public NormalApprovalFlowNode(String id, Double x, Double y) {
        super(id, x, y);
    }

    @Override
    public String getApproverId() {
        return this.approverId;
    }

    @Override
    public ApprovalStatusEnum getApprovalStatus() {
        return this.status;
    }
}
