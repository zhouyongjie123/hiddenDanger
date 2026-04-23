package com.zyj.hiddendanger.model.service.flow.approval.domain.node;

import com.zyj.hiddendanger.core.status.AbstractStatusMachine;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.RejectApprovalEvent;

import java.util.Map;

public class ApprovalFlowNodeStatusMachine extends AbstractStatusMachine<ApprovalStatusEnum, Class<? extends AbstractApprovalFlowEdgeEvent>> {
    public ApprovalFlowNodeStatusMachine() {
        super(Map.of(
                ApprovalStatusEnum.PENDING,
                Map.of(
                        // 待处理->审批通过
                        AcceptApprovalEvent.class, ApprovalStatusEnum.ACCEPTED,
                        // 待处理->审批拒绝
                        RejectApprovalEvent.class, ApprovalStatusEnum.REJECTED
                ),
                ApprovalStatusEnum.ACCEPTED,
                Map.of(
                        // 审批通过->审批通过,
                        AcceptApprovalEvent.class, ApprovalStatusEnum.ACCEPTED,
                        // 审批通过->审批拒绝
                        RejectApprovalEvent.class, ApprovalStatusEnum.REJECTED
                ),
                ApprovalStatusEnum.REJECTED,
                Map.of(
                        // 审批拒绝->审批通过
                        AcceptApprovalEvent.class, ApprovalStatusEnum.ACCEPTED,
                        // 审批拒绝->审批拒绝
                        RejectApprovalEvent.class, ApprovalStatusEnum.REJECTED
                )
        ));
    }

    @Override
    public ApprovalStatusEnum doTransition(
            ApprovalStatusEnum currentStatus, Class<? extends AbstractApprovalFlowEdgeEvent> eventClass) {
        Map<Class<? extends AbstractApprovalFlowEdgeEvent>, ApprovalStatusEnum> allowed = transitions.get(
                currentStatus);
        if (allowed == null || !allowed.containsKey(eventClass)) {
            throw new IllegalStateException("unsupported transition: " + currentStatus + " -> " + eventClass);
        }
        return allowed.get(eventClass);
    }

    private static final class InstanceHolder {
        public static final ApprovalFlowNodeStatusMachine INSTANCE = new ApprovalFlowNodeStatusMachine();
    }

    // 利用静态内部类实现单例模式
    public static ApprovalFlowNodeStatusMachine getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
