package com.zyj.hiddendanger.model.service.flow.approval.domain.node.status;

import com.zyj.hiddendanger.core.status.AbstractStatusMachine;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.event.ApprovalFlowNodeStatusEventEnum;

import java.util.Map;

public class ApprovalFlowNodeStatusMachine extends AbstractStatusMachine<ApprovalStatusEnum, ApprovalFlowNodeStatusEventEnum> {
    public ApprovalFlowNodeStatusMachine() {
        super(Map.of(
                ApprovalStatusEnum.PENDING,
                Map.of(
                        // 待处理->处理中
                        ApprovalFlowNodeStatusEventEnum.PROCESS, ApprovalStatusEnum.PROCESSING
                ),
                ApprovalStatusEnum.PROCESSING,
                Map.of(
                        // 处理中->审批通过
                        ApprovalFlowNodeStatusEventEnum.ACCEPT, ApprovalStatusEnum.ACCEPTED,
                        // 处理中->审批拒绝
                        ApprovalFlowNodeStatusEventEnum.REJECT, ApprovalStatusEnum.REJECTED
                ),
                ApprovalStatusEnum.ACCEPTED,
                Map.of(
                        // 审批通过->审批通过,
                        ApprovalFlowNodeStatusEventEnum.ACCEPT, ApprovalStatusEnum.ACCEPTED,
                        // 审批通过->审批拒绝
                        ApprovalFlowNodeStatusEventEnum.REJECT, ApprovalStatusEnum.REJECTED,
                        // 审批通过->处理中
                        ApprovalFlowNodeStatusEventEnum.PROCESS, ApprovalStatusEnum.PROCESSING
                ),
                ApprovalStatusEnum.REJECTED,
                Map.of(
                        // 审批拒绝->审批通过
                        ApprovalFlowNodeStatusEventEnum.ACCEPT, ApprovalStatusEnum.ACCEPTED,
                        // 审批拒绝->审批拒绝
                        ApprovalFlowNodeStatusEventEnum.REJECT, ApprovalStatusEnum.REJECTED,
                        // 审批拒绝->处理中
                        ApprovalFlowNodeStatusEventEnum.PROCESS, ApprovalStatusEnum.PROCESSING
                )
        ));
    }

    @Override
    public ApprovalStatusEnum doTransition(
            ApprovalStatusEnum currentStatus, ApprovalFlowNodeStatusEventEnum event) {
        Map<ApprovalFlowNodeStatusEventEnum, ApprovalStatusEnum> allowed = transitions.get(
                currentStatus);
        if (allowed == null || !allowed.containsKey(event)) {
            throw new IllegalStateException("unsupported transition: " + currentStatus + "trans by " + event);
        }
        return allowed.get(event);
    }

    private static final class InstanceHolder {
        public static final ApprovalFlowNodeStatusMachine INSTANCE = new ApprovalFlowNodeStatusMachine();
    }

    // 利用静态内部类实现单例模式
    public static ApprovalFlowNodeStatusMachine getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
