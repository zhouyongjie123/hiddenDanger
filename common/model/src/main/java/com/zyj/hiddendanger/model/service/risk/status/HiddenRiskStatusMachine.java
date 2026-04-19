package com.zyj.hiddendanger.model.service.risk.status;

import com.zyj.hiddendanger.core.status.AbstractStatusMachine;
import com.zyj.hiddendanger.model.domain.HiddenRisk;

import java.util.Map;

public class HiddenRiskStatusMachine extends AbstractStatusMachine<HiddenRisk.RiskStatus, HiddenRisk.RiskEvent> {

    @Override
    public HiddenRisk.RiskStatus doTransition(HiddenRisk.RiskStatus currentStatus, HiddenRisk.RiskEvent event) {
        Map<HiddenRisk.RiskEvent, HiddenRisk.RiskStatus> allowed = transitions.get(currentStatus);
        if (allowed == null || !allowed.containsKey(event)) {
            throw new IllegalStateException("unsupported transition: " + currentStatus + " -> " + event);
        }
        return allowed.get(event);
    }

    private HiddenRiskStatusMachine() {
        super(Map.of(
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
        ));
    }

    private static final class InstanceHolder {
        private static final HiddenRiskStatusMachine INSTANCE = new HiddenRiskStatusMachine();
    }

    // 利用静态内部类实现单例模式
    public static HiddenRiskStatusMachine getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
