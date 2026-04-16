package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.domain.FlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowGraph;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AbstractApprovalFlowGraph implements FlowGraph<AbstractApprovalFlowEdgeEvent> {
    // 流程名字
    protected String processName;

    // 业务id
    protected String businessId;

    // 节点列表
    protected List<FlowNode> nodeList;

    // 边列表
    protected List<FlowEdge<AbstractApprovalFlowEdgeEvent>> edgeList;

    // 当前节点（分布式状态统一）
    protected String currentNodeId;

    /**
     * 图
     */
    protected Integer[][] originalGraph;

    private Boolean isMatrix = Boolean.FALSE;

    protected Integer getGraphDimension() {
        // 获取矩阵的维度
        if (this.isMatrix) {
            return this.originalGraph.length;
        }
        if (!this.isMatrix()) {
            throw new RuntimeException("该图不是矩阵");
        }
        return this.originalGraph.length;
    }

    /**
     * 判断 Integer[][] 二维数组是否为合法矩阵
     */
    protected boolean isMatrix() {
        // 1. 数组为 null，不是矩阵
        if (this.originalGraph == null) {
            return false;
        }

        // 2. 空数组（0行），是合法矩阵
        if (this.originalGraph.length == 0) {
            return true;
        }

        // 3. 获取第一行的长度作为标准
        int rowLength = this.originalGraph[0].length;

        // 4. 遍历所有行，检查长度是否一致
        for (Integer[] row : this.originalGraph) {
            // 任意一行为 null 或长度不同 → 不是矩阵
            if (row == null || row.length != rowLength) {
                return false;
            }
        }
        // 所有行长度相同 → 是矩阵
        this.isMatrix = Boolean.TRUE;
        return true;
    }
}
