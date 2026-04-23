package com.zyj.hiddenrisk.flow;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.FlowApplication;
import com.zyj.hiddendanger.flow.service.FlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FlowApplication.class)
public class TestGraph {
    @BeforeEach
    public void setUp() {
        UserIdContextHolder.set("2036347045152862209");
    }

    @Resource
    private FlowProcessService flowProcessService;

    @Test
    public void testSaveProcess() {
        Integer[][] graph = new Integer[][]{
                {0, 0b01, 0, 0, 0},
                {0, 0, 0b01, 0, 0},
                {0, 0, 0, 0b01, 0},
                {0, 0b10, 0, 0, 0b01},
                {0, 0, 0, 0, 0}
        };
        // 审批人id序列
        String[] approverIds = new String[]{"2036347045152862209", "2038875970340347906", "2038877752227487745"};
        ApprovalFlowCreateDTO dto = new ApprovalFlowCreateDTO().setProcessName("测试创建流程")
                                                               .setBusinessId("123")
                                                               .setGraph(new ApprovalFlowGraph(graph))
                                                               .setApproverIds(approverIds);
        // 创建一个审批流程
        flowProcessService.createApprovalProcess(dto);
    }
}
