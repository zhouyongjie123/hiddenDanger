package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowGraph;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public abstract class AbstractApprovalFlowGraph implements FlowGraph<AbstractApprovalFlowEdgeEvent> {
    // 流程名字
    protected String processName;

    // 业务id
    protected String businessId;

    /**
     * 图
     */
    protected Integer[][] originalGraph;

    protected Integer dimension;

    public AbstractApprovalFlowGraph(Integer[][] originalGraph, String processName, String businessId) {
        if (!isMatrix(originalGraph)) {
            throw new RuntimeException("图数据不合法,不是矩阵");
        }
        this.originalGraph = originalGraph;
        this.dimension = originalGraph.length;
        this.processName = processName;
        this.businessId = businessId;
    }

    /**
     * 判断 Integer[][] 二维数组是否为合法矩阵
     */
    private boolean isMatrix(Integer[][] graph) {
        // 1. 数组为 null，不是矩阵
        if (graph == null) {
            return false;
        }

        // 2. 空数组（0行），是合法矩阵
        if (graph.length == 0) {
            return true;
        }

        // 3. 获取第一行的长度作为标准
        int rowLength = graph[0].length;

        // 4. 遍历所有行，检查长度是否一致
        for (Integer[] row : graph) {
            // 任意一行为 null 或长度不同 → 不是矩阵
            if (row == null || row.length != rowLength) {
                return false;
            }
        }
        // 所有行长度相同 → 是矩阵
        return true;
    }

    // 邻接矩阵 的 BFS
    protected boolean hasPathBFS() {
        int n = this.getOriginalGraph().length;
        int v0 = 0;
        int vn = getDimension() - 1;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v0);
        visited[v0] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (u == vn) {
                return true;
            }

            // 邻接矩阵：遍历所有节点
            for (int v = 0; v < n; v++) {
                if (this.getOriginalGraph()[u][v] == 1 && !visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return false;
    }
}
