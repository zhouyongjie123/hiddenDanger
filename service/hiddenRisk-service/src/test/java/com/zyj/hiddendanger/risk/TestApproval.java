package com.zyj.hiddendanger.risk;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureApprovalDTO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureApprovalService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HiddenRiskApplication.class)
public class TestApproval {
    @Resource
    private RectificationMeasureApprovalService rectificationMeasureApprovalService;

    @BeforeEach
    public void init() {
        UserIdContextHolder.set("2036347045152862209");
    }

    @Test
    public void testApproval() {
        RectificationMeasureApprovalDTO dto = new RectificationMeasureApprovalDTO();
        dto.setRectificationMeasureId("66934932227");
        dto.setApprovalMessage("整改很好,给予通过");
        rectificationMeasureApprovalService.approvalAccept(dto);
    }
}
