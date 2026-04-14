package com.zyj.hiddendanger.flow.infrustructure.flow;

public interface EventBus {
    // 注册监听
    void register(String businessId, FlowProcess process);

    // 发布事件
    void publish(FlowEdgeEvent event);
}
