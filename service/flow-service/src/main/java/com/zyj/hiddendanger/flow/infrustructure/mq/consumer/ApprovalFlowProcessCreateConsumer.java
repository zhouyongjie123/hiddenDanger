package com.zyj.hiddendanger.flow.infrustructure.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.infrustructure.mq.message.ApprovalFlowProcessCreateMessage;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowEdgeMapper;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowNodeMapper;
import com.zyj.hiddendanger.flow.mapper.FlowEdgeMapper;
import com.zyj.hiddendanger.flow.mapper.FlowNodeMapper;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.event.ApprovalFlowNodeStatusEventEnum;
import com.zyj.hiddendanger.mq.MessageHeaderConstant;
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
        topic = "approval-flow-process-create",
        consumerGroup = "approval-flow-process-create-group"
)
@RequiredArgsConstructor
public class ApprovalFlowProcessCreateConsumer implements RocketMQListener<MessageExt> {
    private final FlowNodeMapper flowNodeMapper;

    private final FlowEdgeMapper flowEdgeMapper;

    private final ApprovalFlowNodeMapper approvalFlowNodeMapper;

    private final ApprovalFlowEdgeMapper approvalFlowEdgeMapper;

    @Override
    @Idempotent(idempotentKey = "#messageExt.getMsgId()")
    public void onMessage(MessageExt messageExt) {
        try {
            // 1. 恢复上下文
            String userId = messageExt.getProperty(MessageHeaderConstant.USER_ID);
            UserIdContextHolder.set(userId);

            // 2. 解析消息 (启用 SupportClassForName 特性以支持 Class 类型反序列化)
            ApprovalFlowProcessCreateMessage message = JSON.parseObject(
                    messageExt.getBody(),
                    ApprovalFlowProcessCreateMessage.class,
                    JSONReader.Feature.SupportClassForName);

            // 将第一个节点的状态推进为处理中
            message.getNodeList().get(0).transition(ApprovalFlowNodeStatusEventEnum.PROCESS);
            flowNodeMapper.insertBatch(message.getNodeList());
            flowEdgeMapper.insertBatch(message.getEdgeList());
            approvalFlowNodeMapper.insertBatch(message.getNodeList());
            approvalFlowEdgeMapper.insertBatch(message.getEdgeList());
        } finally {
            UserIdContextHolder.remove();
        }
    }
}
