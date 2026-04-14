package com.zyj.hiddenrisk.flow;

import com.zyj.hiddendanger.flow.FlowApplication;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FlowApplication.class)
public class TestRocketMQ {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void testTemplate() {
        SendResult sendResult = rocketMQTemplate.syncSend("tag:", "我是一个同步简单消息");
        System.out.println(sendResult.getSendStatus());
        System.out.println(sendResult.getMsgId());
        System.out.println(sendResult.getMessageQueue());
    }
}
