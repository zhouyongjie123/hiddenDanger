package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalFlowProcessServiceImpl implements ApprovalFlowProcessService {
    @Override
    @Transactional
    public void handleEvent(AbstractApprovalFlowEdgeEvent event) {
        String approvalMessage = event.getApprovalMessage();
        String eventId = event.getEventId();
        ApprovalFlowNode sourceNode = event.getSourceNode();
        String businessId = event.getBusinessId();
        // todo 1.根据业务Id加载流程
        FlowProcess<AbstractApprovalFlowEdgeEvent> flowProcess = new FlowProcess<>();
        // 2.找到当前节点的所有出边
        List<FlowEdge<AbstractApprovalFlowEdgeEvent>> outEdges = flowProcess
                .getEdgeList()
                .stream()
                .filter(edge -> edge.getSourceNodeId().equals(sourceNode.getId()))
                .toList();
        // 3.找到能响应事件的边
        for (FlowEdge<AbstractApprovalFlowEdgeEvent> edge : outEdges) {
            if (edge.isSupportedEvent(event)) {
                // 4.推进节点
                flowProcess.setCurrentNodeId(edge.getTargetNodeId());
                break;
            }
        }
        // todo 5.保存新状态
    }
}
