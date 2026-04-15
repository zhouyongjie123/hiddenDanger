package com.zyj.hiddenrisk.flow;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.FlowApplication;
import com.zyj.hiddendanger.flow.mapper.FlowEdgeMapper;
import com.zyj.hiddendanger.flow.mapper.FlowProcessMapper;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.AcceptApprovalEvent;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = FlowApplication.class)
public class TestFlow {
    @Resource
    private FlowEdgeMapper flowEdgeMapper;

    @BeforeEach
    public void before() {
        UserIdContextHolder.set("2036347045152862209");
    }

    @Test
    public void testFlowEdgeMapperInsert() {
        FlowEdge<AcceptApprovalEvent> edge = new FlowEdge<>();
        edge.setSourceNodeId("1")
            .setTargetNodeId("2");
        List<Class<AcceptApprovalEvent>> list = new ArrayList<>();
        list.add(AcceptApprovalEvent.class);
        edge.setSupportedEventList(list);
        flowEdgeMapper.insert(edge);

    }

    @Resource
    private FlowProcessMapper<AbstractApprovalFlowEdgeEvent> approvalFlowProcessMapper;

    @Test
    public void testProcessMapperSelect() {
        FlowProcess<AbstractApprovalFlowEdgeEvent> approvalFlowProcess = approvalFlowProcessMapper.getApprovalFlowProcess(
                "99238464");
        System.out.println(approvalFlowProcess.toString());
    }

}
