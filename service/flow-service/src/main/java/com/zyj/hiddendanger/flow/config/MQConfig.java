package com.zyj.hiddendanger.flow.config;

import com.zyj.hiddendanger.flow.infrustructure.mq.transaction.ApprovalFlowProcessCreateTransactionListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${rocketmq.name-server}")
    private String nameServerAddr;

    /**
     * 创建审批流程事务消息生产者,解决多表insert事务
     */
    @Bean
    public TransactionMQProducer approvalFlowProcessCreateTransactionMQProducer(
            ApprovalFlowProcessCreateTransactionListener listener) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer();
        producer.setProducerGroup("approval-flow-process-creation-producer");
        producer.setTransactionListener(listener);
        producer.setNamesrvAddr(nameServerAddr);
        // 设置producer的状态
        producer.start();
        return producer;
    }
}
