package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.flow.service.FlowService;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.ApprovalFlowEdge;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowServiceImpl implements FlowService {
    private final IdGenerator<String> idGenerator;

    @Override
    public void createApprovalProcess(ApprovalFlowCreateDTO dto) {
        ApprovalFlowGraph graph = dto.getGraph();
        List<ApprovalFlowCreateDTO.ApprovalFlowNodeInfo> nodeInfoList = dto.getNodeInfoList();
        List<ApprovalFlowCreateDTO.ApprovalFlowEdgeInfo> edgeInfoList = dto.getEdgeInfoList();
        Integer[][] originalGraph = graph.getOriginalGraph();
        // 先创建processId
        String processId = idGenerator.generate();

        // 节点列表
        List<ApprovalFlowNode> nodeList = new ArrayList<>();
        // 边列表
        List<ApprovalFlowEdge> edgeList = new ArrayList<>();

        FlowProcess<AbstractApprovalFlowEdgeEvent> flowProcess = new FlowProcess<>();
        flowProcess.setProcessName(dto.getProcessName())
                   .setBusinessId(dto.getBusinessId())
                   .setNodeList(nodeList)
                   .setCurrentNodeId(nodeList.get(1).getId())
                   .setEdgeList(edgeList)
                   .setId(processId);
    }
}
