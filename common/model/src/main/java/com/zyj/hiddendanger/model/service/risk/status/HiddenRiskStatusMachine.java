package com.zyj.hiddendanger.model.service.risk.status;

import com.zyj.hiddendanger.core.status.AbstractStatusMachine;
import com.zyj.hiddendanger.model.domain.HiddenRisk;

import java.util.Map;

public class HiddenRiskStatusMachine extends AbstractStatusMachine<HiddenRisk.RiskStatus, HiddenRisk.RiskEvent> {

    @Override
    public HiddenRisk.RiskStatus doTransition(HiddenRisk.RiskStatus currentStatus, HiddenRisk.RiskEvent event) {
        Map<HiddenRisk.RiskEvent, HiddenRisk.RiskStatus> allowed = transitions.get(currentStatus);
        if (allowed == null || !allowed.containsKey(event)) {
            throw new IllegalStateException("unsupported transition: " + currentStatus + " trans by  " + event);
        }
        return allowed.get(event);
    }

    private HiddenRiskStatusMachine() {
        super(Map.of(
                HiddenRisk.RiskStatus.WAIT_RECTIFY,
                Map.of(
                        // 待整改-->整改中
                        HiddenRisk.RiskEvent.RECTIFY, HiddenRisk.RiskStatus.RECTIFYING),
                HiddenRisk.RiskStatus.RECTIFYING,
                Map.of(
                        // 整改中->整改报告已提交
                        HiddenRisk.RiskEvent.SUBMIT_RECTIFY_REPORT, HiddenRisk.RiskStatus.RECTIFY_REPORT_SUBMITTED),
                HiddenRisk.RiskStatus.RECTIFY_REPORT_SUBMITTED,
                Map.of(
                        // 整改报告已提交->已闭环
                        HiddenRisk.RiskEvent.ACCEPT, HiddenRisk.RiskStatus.CLOSED,
                        // 整改报告已提交->整改中
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
