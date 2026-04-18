package com.zyj.hiddendanger.flow.config;

import com.zyj.hiddendanger.flow.infrustructure.mq.transaction.ApprovalFlowProcessCreateTransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    /**
     * 创建审批流程事务消息生产者,解决多表insert事务
     */
    @Bean
    public TransactionMQProducer approvalFlowProcessCreateTransactionMQProducer(
            ApprovalFlowProcessCreateTransactionListener listener) {
        TransactionMQProducer producer = new TransactionMQProducer();
        producer.setProducerGroup("approval-flow-process-creation-producer");
        producer.setTransactionListener(listener);
        return producer;
    }
}
