package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowNodeMapper;
import com.zyj.hiddendanger.flow.mapper.ApprovalRecordMapper;
import com.zyj.hiddendanger.flow.mapper.FlowProcessMapper;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.model.domain.ApprovalRecord;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.ApprovalFlowEdge;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNodeStatusMachine;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalFlowProcessServiceImpl implements ApprovalFlowProcessService {
    private final FlowProcessMapper flowProcessMapper;

    private final ApprovalFlowNodeMapper approvalFlowNodeMapper;

    private final ApprovalRecordMapper approvalRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleEvent(AbstractApprovalFlowEdgeEvent event) {
        String approvalMessage = event.getApprovalMessage();
        String businessId = event.getBusinessId();
        // 1.根据业务Id加载流程
        FlowProcess<ApprovalFlowEdge, ApprovalFlowNode> flowProcess = flowProcessMapper.getApprovalFlowProcess(
                businessId);
        // 2.找到当前节点的所有出边
        String currentNodeId = flowProcess.getCurrentNodeId();
        List<ApprovalFlowEdge> outEdges = flowProcess
                .getEdgeList()
                .stream()
                .filter(edge -> edge.getSourceNodeId().equals(currentNodeId))
                .toList();
        // 3.找到当前节点
        ApprovalFlowNode currentNode = flowProcess.getNodeList().stream()
                                                  .filter(node -> node.getId().equals(currentNodeId))
                                                  .findFirst()
                                                  .orElseThrow(() -> new RuntimeException("找不到当前节点"));
        ApprovalRecord approvalRecord = new ApprovalRecord().setApprovalFlowNodeId(currentNodeId)
                                                            .setApproverId(UserIdContextHolder.get())
                                                            .setApprovalMessage(approvalMessage);
        approvalRecord.setStatus(ApprovalStatusEnum.of(event));

        currentNode.getApprovalRecords().add(new ApprovalRecord());
        // 4.找到能响应事件的边
        for (FlowEdge<AbstractApprovalFlowEdgeEvent> edge : outEdges) {
            if (edge.isSupportedEvent(event)) {
                // 5.推进图节点,推进审批节点自身的状态
                ApprovalFlowNodeStatusMachine.getInstance().transition(currentNode.getStatus(), event.getClass(), true);
                flowProcess.setCurrentNodeId(edge.getTargetNodeId());
                break;
            }
        }
        // 6.保存新状态
        // 保存新流程
        flowProcessMapper.updateById(flowProcess);
        // 保存审批节点
        approvalFlowNodeMapper.updateById(currentNode);
        // 保存审批记录
        approvalRecordMapper.insert(approvalRecord);
    }
}
