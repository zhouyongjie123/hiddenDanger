package com.zyj.hiddendanger.ai.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashscope")
public class DashScopeTestController implements InitializingBean {

    @Resource(name = "dashScopeChatModel")
    private ChatModel dashScopeChatModel;

    private ChatClient chatClient;

    @GetMapping("/test")
    public String test() {
        return chatClient.prompt("你是谁").call().content();
//        return chatClient.call("你是谁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        chatClient = ChatClient.builder(dashScopeChatModel)
                               // 实现 Logger 的 Advisor
                               .defaultAdvisors(
                                       new SimpleLoggerAdvisor()
                               ).defaultSystem("请用英文回答问题")
                               // 设置 ChatClient 中 ChatModel 的 Options 参数
                               .defaultOptions(
                                       DashScopeChatOptions.builder()
                                                           .temperature(0.7)
                                                           .build()
                               )
                               .build();
    }
}
