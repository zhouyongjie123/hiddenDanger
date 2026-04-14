package com.zyj.hiddendanger.flow.infrustructure.approval.node;

import com.zyj.hiddendanger.flow.infrustructure.FlowNode;
import com.zyj.hiddendanger.flow.infrustructure.approval.enums.ApprovalStatusEnum;

/**
 * 审批节点
 */
public interface ApprovalFlowNode extends FlowNode {
    String getApproverId();

    ApprovalStatusEnum getApprovalStatus();
}
