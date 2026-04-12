package com.zyj.hiddendanger.model.service.risk.status;

import com.zyj.hiddendanger.core.status.StateMachine;
import com.zyj.hiddendanger.model.domain.HiddenRisk;

import java.util.Map;

public class HiddenRiskStateMachine implements StateMachine<HiddenRisk.RiskStatus, HiddenRisk.RiskEvent> {
    // key: 当前状态, value: 允许的事件->目标状态
    protected Map<HiddenRisk.RiskStatus, Map<HiddenRisk.RiskEvent, HiddenRisk.RiskStatus>> transitions;

    @Override
    public HiddenRisk.RiskStatus transition(HiddenRisk.RiskStatus currentState, HiddenRisk.RiskEvent event) {
        beforeTransition(currentState, event);
        Map<HiddenRisk.RiskEvent, HiddenRisk.RiskStatus> allowed = transitions.get(currentState);
        if (allowed == null || !allowed.containsKey(event)) {
            throw new IllegalStateException("unsupported transition: " + currentState + " -> " + event);
        }
        HiddenRisk.RiskStatus targetStatus = allowed.get(event);
        afterTransition(currentState, event, targetStatus);
        return targetStatus;
    }

    @Override
    public HiddenRisk.RiskStatus transition(
            HiddenRisk.RiskStatus currentState, HiddenRisk.RiskEvent event,
            Boolean isOverride) {
        if (isOverride) {
            // 原地覆盖源对象
            currentState = transition(currentState, event);
            return currentState;
        } else {
            return transition(currentState, event);
        }
    }

    private HiddenRiskStateMachine() {
        this.transitions = Map.of(
                // 待整改-->整改中
                HiddenRisk.RiskStatus.WAIT_RECTIFY,
                Map.of(
                        HiddenRisk.RiskEvent.RECTIFY, HiddenRisk.RiskStatus.RECTIFYING,
                        // 撤销
                        HiddenRisk.RiskEvent.REVOKE, HiddenRisk.RiskStatus.CANCELED),
                // 整改中-->待验收
                HiddenRisk.RiskStatus.RECTIFYING,
                Map.of(
                        HiddenRisk.RiskEvent.RECTIFY_COMPLETE, HiddenRisk.RiskStatus.WAIT_ACCEPTANCE,
                        // 撤销
                        HiddenRisk.RiskEvent.REVOKE, HiddenRisk.RiskStatus.CANCELED),
                // 待验收---同意--->已闭环 待验收---拒绝--->整改中
                HiddenRisk.RiskStatus.WAIT_ACCEPTANCE,
                Map.of(
                        HiddenRisk.RiskEvent.ACCEPT, HiddenRisk.RiskStatus.CLOSED,
                        HiddenRisk.RiskEvent.REJECT, HiddenRisk.RiskStatus.RECTIFYING)
        );
    }

    private static final class InstanceHolder {
        private static final HiddenRiskStateMachine INSTANCE = new HiddenRiskStateMachine();
    }

    // 利用静态内部类实现单例模式
    public static HiddenRiskStateMachine getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
