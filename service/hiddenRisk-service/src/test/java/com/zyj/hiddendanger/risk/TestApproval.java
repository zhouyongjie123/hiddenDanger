package com.zyj.hiddendanger.risk;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskApprovalService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HiddenRiskApplication.class)
public class TestApproval {
    @Resource
    private HiddenRiskApprovalService hiddenRiskApprovalService;

    @BeforeEach
    public void init() {
        UserIdContextHolder.set("2036347045152862209");
    }

    @Test
    public void testApproval() {
        HiddenRiskApprovalDTO dto = new HiddenRiskApprovalDTO();
        dto.setHiddenRiskId("92612219037");
        dto.setApprovalMessage("整改很好,给予通过");
        hiddenRiskApprovalService.approvalAccept(dto);
    }
}
