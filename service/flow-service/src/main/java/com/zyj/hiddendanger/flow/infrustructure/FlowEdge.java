package com.zyj.hiddendanger.flow.infrustructure;

/**
 * 流程边(有向边)
 */
public interface FlowEdge<E> {
    // 边的id
    String getEdgeId();

    // 边的源节点id
    String getSourceNodeId();

    // 边的目标节点id
    String getTargetNodeId();

    // 边支持的事件
    FlowEdgeEvent getSupportedEvent();
}
