package com.zyj.hiddendanger.flow.infrustructure.publisher;

import com.zyj.hiddendanger.flow.infrustructure.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApprovalFlowEventPublisher {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 节点触发事件
     */
    public void publish(AbstractApprovalFlowEdgeEvent event) {
        rocketMQTemplate.convertAndSend("approval-flow-event-topic", event);
    }
}
