package com.zyj.hiddendanger.model.service.flow.infrustructure;

public interface FlowEdgeEvent {
    // 业务号
    String getBusinessId();

    // 事件Id用于幂等
    String getEventId();
}
