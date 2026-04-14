package com.zyj.hiddenrisk.flow;

import com.zyj.hiddendanger.flow.FlowApplication;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.risk.status.HiddenRiskStatusMachine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FlowApplication.class)
public class TestStatusMachine {
    @Test
    public void test() {
        System.out.println(HiddenRiskStatusMachine
                                   .getInstance()
                                   .transition(HiddenRisk.RiskStatus.WAIT_RECTIFY, HiddenRisk.RiskEvent.RECTIFY_COMPLETE));
    }
}
