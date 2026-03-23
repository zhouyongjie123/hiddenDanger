package com.zyj.hiddendanger.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class TestDashScope {
    @Resource(name = "dashScopeChatModel")
    private ChatModel dashScopeChatModel;

    @Test
    public void test() {
        System.out.println(dashScopeChatModel.call("介绍一下java"));
    }
}
