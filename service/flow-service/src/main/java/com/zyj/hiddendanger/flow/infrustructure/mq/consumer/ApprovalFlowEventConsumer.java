package com.zyj.hiddendanger.flow.infrustructure.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.mq.MessageHeaderConstant;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        topic = "approval-flow-event",
        consumerGroup = "approval-flow-event-group"
)
@RequiredArgsConstructor
public class ApprovalFlowEventConsumer implements RocketMQListener<MessageExt> {
    private final ApprovalFlowProcessService approvalFlowProcessService;

    @Override
    @Idempotent(idempotentKey = "#messageExt.getMsgId()")
    public void onMessage(MessageExt messageExt) {
        try {
            AbstractApprovalFlowEdgeEvent event = JSON.parseObject(
                    messageExt.getBody(),
                    AbstractApprovalFlowEdgeEvent.class);
            // 恢复上下文
            String userId = messageExt.getProperty(MessageHeaderConstant.USER_ID);
            UserIdContextHolder.set(userId);
            // 处理事件，推进节点
            approvalFlowProcessService.handleEvent(event);
        } catch (Exception e) {
            log.error("流程处理失败，消息将重新投递", e);
            // 抛出异常 → RocketMQ 自动重试
            throw e;
        }
    }
}
