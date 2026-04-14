package com.zyj.hiddendanger.flow.infrustructure.consumer;

import com.zyj.hiddendanger.flow.infrustructure.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        topic = "approval-flow-event-topic",
        consumerGroup = "approval-flow-consumer-group"
)
@RequiredArgsConstructor
public class ApprovalFlowEventConsumer implements RocketMQListener<AbstractApprovalFlowEdgeEvent> {
    private final ApprovalFlowProcessService approvalFlowProcessService;

    @Override
    public void onMessage(AbstractApprovalFlowEdgeEvent event) {
        try {
            // 核心：处理事件，推进节点
            approvalFlowProcessService.handleEvent(event);
        } catch (Exception e) {
            log.error("流程处理失败，消息将重新投递", e);
            // 抛出异常 → RocketMQ 自动重试
            throw e;
        }
    }
}
