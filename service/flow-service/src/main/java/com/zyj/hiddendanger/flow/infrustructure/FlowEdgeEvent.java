package com.zyj.hiddendanger.flow.infrustructure;

public interface FlowEdgeEvent {
    FlowNode getSourceNode();

    // 业务号
    String getBusinessId();
}
