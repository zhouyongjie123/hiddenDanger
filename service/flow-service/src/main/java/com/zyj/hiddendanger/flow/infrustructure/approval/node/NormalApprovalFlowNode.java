package com.zyj.hiddendanger.flow.infrustructure.approval.node;

import com.zyj.hiddendanger.flow.infrustructure.approval.ApprovalStatusEnum;
import com.zyj.hiddendanger.flow.infrustructure.approval.edge.ApprovalFlowEdgeEventEnum;
import org.apache.dubbo.remoting.http12.exception.UnimplementedException;

/**
 * 正常审批节点
 */
public class NormalApprovalFlowNode extends AbstractApprovalFlowNode {
    public NormalApprovalFlowNode(String id, Double x, Double y, ApprovalFlowEdgeEventEnum event) {
        super(id, x, y, event);
    }

    @Override
    public String getApproverId() {
        return this.approverId;
    }

    @Override
    public ApprovalStatusEnum getApprovalStatus() {
        return this.status;
    }

    @Override
    public void emit(ApprovalFlowEdgeEventEnum event) {
        throw new UnimplementedException("未实现触发边的事件");
    }
}
