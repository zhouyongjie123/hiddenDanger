package com.zyj.hiddendanger.model.service.flow.infrustructure;

/**
 * 流程边(有向边)
 */
public interface FlowEdge<E extends FlowEdgeEvent> {
    // 边的id
    String getEdgeId();

    // 边的源节点id
    String getSourceNodeId();

    // 边的目标节点id
    String getTargetNodeId();

    // 判断该边是否支持该事件
    Boolean isSupportedEvent(E event);
}
