package com.zyj.hiddendanger.flow.infrustructure.mq.consumer;

import com.zyj.hiddendanger.flow.infrustructure.mq.message.ApprovalFlowProcessCreateMessage;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowEdgeMapper;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowNodeMapper;
import com.zyj.hiddendanger.flow.mapper.FlowEdgeMapper;
import com.zyj.hiddendanger.flow.mapper.FlowNodeMapper;
import com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        topic = "approval-flow-process-create",
        consumerGroup = "approval-flow-process-create-group"
)
@RequiredArgsConstructor
public class ApprovalFlowProcessCreateConsumer implements RocketMQListener<ApprovalFlowProcessCreateMessage> {
    private final FlowNodeMapper flowNodeMapper;

    private final FlowEdgeMapper flowEdgeMapper;

    private final ApprovalFlowNodeMapper approvalFlowNodeMapper;

    private final ApprovalFlowEdgeMapper approvalFlowEdgeMapper;

    @Override
    // 幂等操作
    @Idempotent(idempotentKey = "#approvalFlowProcessCreateMessage.flowProcessId")
    public void onMessage(ApprovalFlowProcessCreateMessage approvalFlowProcessCreateMessage) {
        System.out.println("分支事务开始");
        // 将FlowNode放入数据库
        flowNodeMapper.insertBatch(approvalFlowProcessCreateMessage.getNodeList());
        // 将FlowEdge放入数据库
        flowEdgeMapper.insertBatch(approvalFlowProcessCreateMessage.getEdgeList());
        // 将ApprovalFlowNode放入数据库
        approvalFlowNodeMapper.insertBatch(approvalFlowProcessCreateMessage.getNodeList());
        // 将ApprovalFlowEdge放入数据库
        approvalFlowEdgeMapper.insertBatch(approvalFlowProcessCreateMessage.getEdgeList());
        System.out.println("分支事务结束");
    }
}
