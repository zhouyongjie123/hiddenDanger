package com.zyj.hiddendanger.model.service.flow.infrustructure;

import com.zyj.hiddendanger.model.domain.FlowNode;

public interface FlowEdgeEvent {
    FlowNode getSourceNode();

    // 业务号
    String getBusinessId();

    // 事件Id用于幂等
    String getEventId();
}
