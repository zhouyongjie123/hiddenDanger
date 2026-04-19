package com.zyj.hiddendanger.risk;

import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskApprovalService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HiddenRiskApplication.class)
public class TestApproval {
    @Resource
    private HiddenRiskApprovalService hiddenRiskApprovalService;

    @Test
    public void testApproval() {
        HiddenRiskApprovalDTO dto = new HiddenRiskApprovalDTO();
        dto.setHiddenRiskId("1");
        dto.setApprovalMessage("通过");
        hiddenRiskApprovalService.approvalAccept(dto);
    }
}
