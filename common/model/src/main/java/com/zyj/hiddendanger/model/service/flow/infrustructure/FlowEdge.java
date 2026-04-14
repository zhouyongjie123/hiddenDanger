package com.zyj.hiddendanger.model.service.flow.infrustructure;

/**
 * 流程边(有向边)
 */
public interface FlowEdge {
    // 边的id
    String getEdgeId();

    // 边的源节点id
    String getSourceNodeId();

    // 边的目标节点id
    String getTargetNodeId();

    // 边支持的事件
    FlowEdgeEvent getSupportedEvent();
}
