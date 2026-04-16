package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowGraph;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractApprovalFlowGraph implements FlowGraph<AbstractApprovalFlowEdgeEvent> {
    /**
     * 流程名字
     */
    protected String processName;

    /**
     * 业务id
     */
    protected String businessId;

    /**
     * 图
     */
    protected Integer[][] originalGraph;

    /**
     * 维度
     */
    protected Integer dimension;

    public AbstractApprovalFlowGraph(Integer[][] originalGraph, String processName, String businessId) {
        if (!isLegal(originalGraph)) {
            throw new RuntimeException("图数据不合法");
        }
        this.originalGraph = originalGraph;
        this.dimension = originalGraph.length;
        this.processName = processName;
        this.businessId = businessId;
    }

    protected abstract Boolean isLegal(Integer[][] originalGraph);
}
