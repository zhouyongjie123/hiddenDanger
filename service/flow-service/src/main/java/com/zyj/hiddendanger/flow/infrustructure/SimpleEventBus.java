package com.zyj.hiddendanger.flow.infrustructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单事件总线(单机版)
 * 节点发布事件
 * 流程订阅事件
 */
public class SimpleEventBus implements EventBus {
    // 单例模式
    public static final SimpleEventBus INSTANCE = new SimpleEventBus();

    // 事件监听器:key=业务id,value=监听业务的process
    private final Map<String, FlowProcess> listeners = new ConcurrentHashMap<>();

    public static SimpleEventBus getInstance() {
        return INSTANCE;
    }

    // 注册监听
    public void register(String businessId, FlowProcess process) {
        listeners.putIfAbsent(businessId, process);
    }

    // 发布事件
    public void publish(FlowEdgeEvent event) {
        FlowProcess flowProcess = listeners.get(event.getBusinessId());
        if (flowProcess != null) {
            flowProcess.onEvent(event);
        }
    }
}
