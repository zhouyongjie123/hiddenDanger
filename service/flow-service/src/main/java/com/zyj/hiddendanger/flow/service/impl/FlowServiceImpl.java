package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.flow.mapper.*;
import com.zyj.hiddendanger.flow.service.FlowService;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.ApprovalFlowEdge;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.ApprovalFlowEdgeEventParser;
import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowServiceImpl implements FlowService {
    private final IdGenerator<String> idGenerator;

    private final FlowProcessMapper flowProcessMapper;

    private final FlowNodeMapper flowNodeMapper;

    private final FlowEdgeMapper flowEdgeMapper;

    private final ApprovalFlowNodeMapper approvalFlowNodeMapper;

    private final ApprovalFlowEdgeMapper approvalFlowEdgeMapper;

    @Override
    public void createApprovalProcess(ApprovalFlowCreateDTO dto) {
        ApprovalFlowGraph graph = dto.getGraph();
        String[] approverIds = dto.getApproverIds();
        Integer[][] originalGraph = graph.getOriginalGraph();
        // 先创建processId
        String processId = idGenerator.generate();
        // 节点列表
        List<ApprovalFlowNode> nodeList = new ArrayList<>();
        for (String approverId : approverIds) {
            ApprovalFlowNode approvalFlowNode = new ApprovalFlowNode();
            approvalFlowNode.setStatus(ApprovalStatusEnum.PENDING)
                            .setApproverId(approverId)
                            .setApprovalRecords(Collections.emptyList())
                            .setProcessId(processId)
                            .setId(idGenerator.generate());
            nodeList.add(approvalFlowNode);
        }
        // 边列表
        List<ApprovalFlowEdge> edgeList = new ArrayList<>();
        for (int i = 0; i < graph.getDimension(); i++) {
            for (int j = 0; j < graph.getDimension(); j++) {
                if (originalGraph[i][j] != 0) {
                    // 当前位置不为0,有值
                    ApprovalFlowNode sourceNode;
                    ApprovalFlowNode targetNode;
                    if (i == 0) {
                        sourceNode = ApprovalFlowNode.START;
                    } else {
                        sourceNode = nodeList.get(i - 1);
                    }
                    if (j == graph.getDimension() - 1) {
                        targetNode = ApprovalFlowNode.END;
                    } else {
                        targetNode = nodeList.get(j - 1);
                    }
                    Integer eventCode = originalGraph[i][j];
                    List<Class<? extends AbstractApprovalFlowEdgeEvent>> supportedEventClass = ApprovalFlowEdgeEventParser.getSupportedEventClass(
                            eventCode);
                    // 创建一条边
                    ApprovalFlowEdge approvalFlowEdge = new ApprovalFlowEdge();
                    approvalFlowEdge.setProcessId(processId)
                                    .setSourceNodeId(sourceNode.getId())
                                    .setTargetNodeId(targetNode.getId())
                                    .setSupportedEventList(supportedEventClass)
                                    .setId(idGenerator.generate());
                    // 加入到边列表
                    edgeList.add(approvalFlowEdge);
                }
            }
        }

        // 创建一个流程对象
        FlowProcess<AbstractApprovalFlowEdgeEvent> flowProcess = new FlowProcess<>();
        flowProcess.setProcessName(dto.getProcessName())
                   .setBusinessId(dto.getBusinessId())
                   .setNodeList(nodeList)
                   .setEdgeList(edgeList)
                   .setCurrentNodeId(nodeList.get(1).getId())
                   .setId(processId);
        // todo 放入到数据库中
        // 将FlowProcess放入数据库
//        flowProcessMapper.saveFlowProcess(flowProcess);
        // 将FlowNode放入数据库
//        flowNodeMapper.insertBatch(flowProcess.getNodeList());
        // 将FlowEdge放入数据库
        List<? extends FlowEdge<? extends AbstractApprovalFlowEdgeEvent>> edgeList1 = flowProcess.getEdgeList();
        System.out.println(flowEdgeMapper.insertBatch(edgeList1));
        // 将ApprovalFlowNode放入数据库
//        approvalFlowNodeMapper.insert();
        // 将ApprovalFlowEdge放入数据库
//        approvalFlowEdgeMapper.insert();
    }
}
