package com.zyj.hiddendanger.flow.infrustructure.mq.transaction;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyj.hiddendanger.flow.infrustructure.mq.message.ApprovalFlowProcessCreateMessage;
import com.zyj.hiddendanger.flow.mapper.FlowProcessMapper;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ApprovalFlowProcessCreateTransactionListener implements TransactionListener {
    @Resource
    private FlowProcessMapper flowProcessMapper;

    /**
     * 执行了本地事务,直接提交
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        ApprovalFlowProcessCreateMessage dto = JSON.parseObject(
                messageExt.getBody(), ApprovalFlowProcessCreateMessage.class);
        // 查主表是否存在
        return flowProcessMapper.exists(
                new LambdaQueryWrapper<FlowProcess<?, ?>>().eq(
                        FlowProcess::getId, dto.getFlowProcessId())) ? LocalTransactionState.COMMIT_MESSAGE
                : LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
