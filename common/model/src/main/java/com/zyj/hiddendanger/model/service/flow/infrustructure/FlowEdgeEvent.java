package com.zyj.hiddendanger.model.service.flow.infrustructure;

public interface FlowEdgeEvent {
    String getSourceNodeId();

    // 业务号
    String getBusinessId();

    // 事件Id用于幂等
    String getEventId();

    // 获取事件编码
    Integer getEventCode();
}
