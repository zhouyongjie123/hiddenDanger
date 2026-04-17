package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.flow.mapper.FlowProcessMapper;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalFlowProcessServiceImpl implements ApprovalFlowProcessService {
    private final FlowProcessMapper<AbstractApprovalFlowEdgeEvent> flowProcessMapper;

    @Override
    @Transactional
    public void handleEvent(AbstractApprovalFlowEdgeEvent event) {
//        String approvalMessage = event.getApprovalMessage();
//        String eventId = event.getEventId();
//        String sourceNodeId = event.getSourceNodeId();
//        String businessId = event.getBusinessId();
//        // 1.根据业务Id加载流程
//        FlowProcess<AbstractApprovalFlowEdgeEvent> flowProcess = flowProcessMapper.getApprovalFlowProcess(
//                businessId);
//        // 2.找到当前节点的所有出边
//        List<FlowEdge<AbstractApprovalFlowEdgeEvent>> outEdges = flowProcess
//                .getEdgeList()
//                .stream()
//                .filter(edge -> edge.getSourceNodeId().equals(sourceNodeId))
//                .toList();
//        // 3.找到能响应事件的边
//        for (FlowEdge<AbstractApprovalFlowEdgeEvent> edge : outEdges) {
//            if (edge.isSupportedEvent(event)) {
//                // 4.推进节点
//                flowProcess.setCurrentNodeId(edge.getTargetNodeId());
//                break;
//            }
//        }
        // todo 5.保存新状态
    }
}
