package com.zyj.hiddendanger.flow.infrustructure.flow.approval.node;

import com.zyj.hiddendanger.flow.infrustructure.flow.FlowNode;
import com.zyj.hiddendanger.flow.infrustructure.flow.approval.enums.ApprovalStatusEnum;

/**
 * 审批节点
 */
public interface ApprovalFlowNode extends FlowNode {
    String getApproverId();

    ApprovalStatusEnum getApprovalStatus();
}
