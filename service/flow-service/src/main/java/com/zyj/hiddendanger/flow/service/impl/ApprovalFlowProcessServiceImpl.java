package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.flow.infrustructure.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.flow.infrustructure.flow.approval.node.ApprovalFlowNode;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApprovalFlowProcessServiceImpl implements ApprovalFlowProcessService {

    @Override
    @Transactional
    public void handleEvent(AbstractApprovalFlowEdgeEvent event) {
        String approvalMessage = event.getApprovalMessage();
        String eventId = event.getEventId();
        ApprovalFlowNode sourceNode = event.getSourceNode();
        String businessId = event.getBusinessId();
        // 1.根据业务Id加载流程
        // 2.找到房钱节点的所有出边
        // 3.找到能响应事件的边
           // 4.推进节点
        // 5.保存新状态
    }
}
