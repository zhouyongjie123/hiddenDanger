package com.zyj.hiddenrisk.flow;

import com.zyj.hiddendanger.flow.FlowApplication;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.risk.status.HiddenRiskStateMachine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FlowApplication.class)
public class TestStateMachine {
    @Test
    public void test() {
        System.out.println(HiddenRiskStateMachine
                                   .getInstance()
                                   .transition(HiddenRisk.RiskStatus.WAIT_RECTIFY, HiddenRisk.RiskEvent.RECTIFY_COMPLETE));
    }
}
