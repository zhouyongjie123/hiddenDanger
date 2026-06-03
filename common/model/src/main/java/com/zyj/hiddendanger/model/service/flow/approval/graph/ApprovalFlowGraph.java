package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.Queue;

public class ApprovalFlowGraph extends AbstractApprovalFlowGraph {
    @JsonCreator
    public ApprovalFlowGraph(@JsonProperty("originalGraph") Integer[][] originalGraph) {
        super(originalGraph);
    }

    @Override
    public Boolean isLegal(Integer[][] originalGraph) {
// ===================== 1. 基础合法性校验 =====================
        // 空指针判断
        if (originalGraph == null || originalGraph.length == 0) {
            return false;
        }
        // 必须是方阵（邻接矩阵要求）
        if (!isMatrix(originalGraph)) {
            return false;
        }

        int nodeCount = originalGraph.length;

        // 必须至少包含：起点 + 至少1个业务节点 + 终点
        if (nodeCount < 3) {
            return false;
        }

        // ===================== 2. 校验终点（最后一个节点）规则 =====================
        // 规则：终点无出边，有且仅有1条入边（值为APPROVE）
        if (!checkEndNode(originalGraph, nodeCount)) {
            return false;
        }

        // ===================== 3. 校验起点（第一个节点）规则 =====================
        // 规则：起点无入边，有且仅有1条出边（值为APPROVE）
        if (!checkStartNode(originalGraph, nodeCount)) {
            return false;
        }

        // ===================== 4. 校验中间业务节点规则 =====================
        // 规则1：无自环
        // 规则2：必须同时有入度和出度
        // 规则3：出度和必须=3（通过1+拒绝2）
        if (!checkMiddleNodes(originalGraph, nodeCount)) {
            return false;
        }

        // ===================== 5. 校验连通性：起点必须能到达终点 =====================
        if (!hasPathBFS(originalGraph, nodeCount)) {
            return false;
        }

        // 所有校验通过
        return true;
    }

    /**
     * 校验终点（最后一个节点）
     * 1. 终点不能有任何出边（整行=0）
     * 2. 终点只能有 1 个入边，且值必须是 APPROVE(1)
     */
    private boolean checkEndNode(Integer[][] graph, int nodeCount) {
        int endIndex = nodeCount - 1;

        // 1. 终点出边必须全为0
        for (int j = 0; j < nodeCount; j++) {
            if (graph[endIndex][j] != 0) {
                return false;
            }
        }

        // 2. 终点入边统计：只能有一个1，其余必须是0
        int approveInCount = 0;
        for (int i = 0; i < nodeCount; i++) {
            int value = graph[i][endIndex];
            // 入边只能是0或1
            if (value != 0 && value != APPROVE) {
                return false;
            }
            if (value == APPROVE) {
                approveInCount++;
            }
        }

        // 必须有且仅有1条通过入边
        return approveInCount == 1;
    }

    /**
     * 校验起点（第一个节点）
     * 1. 起点不能有任何入边（整列=0）
     * 2. 起点只能有 1 个出边，且值必须是 APPROVE(1)
     */

    private boolean checkStartNode(Integer[][] graph, int nodeCount) {
        int startIdx = 0;
        int rejectInCnt = 0;

        // 遍历第0列：统计起点所有入边
        for (int i = 0; i < nodeCount; i++) {
            int val = graph[i][startIdx];
            // 入边只允许 0 或 拒绝2
            if (val != 0 && val != REJECT) {
                return false;
            }
            // 统计拒绝入边数量
            if (val == REJECT) {
                rejectInCnt++;
            }
        }

        // 强制：有且只能有一条 REJECT 入边
        return rejectInCnt == 1;
    }


    /**
     * 校验所有中间节点（非起点、非终点）
     * 1. 无自环（graph[i][i] = 0）
     * 2. 必须有出度（行和≠0）
     * 3. 必须有入度（列和≠0）
     * 4. 出度和必须 = 3（1通过+2拒绝）
     */
    private boolean checkMiddleNodes(Integer[][] graph, int nodeCount) {
        for (int i = 1; i < nodeCount - 1; i++) {
            // 1. 禁止自环
            if (graph[i][i] != 0) {
                return false;
            }

            // 2. 计算当前节点出度和
            int outSum = 0;
            boolean hasOut = false;
            for (int j = 0; j < nodeCount; j++) {
                int val = graph[i][j];
                outSum += val;
                if (val != 0) hasOut = true;
            }

            // 出度和必须严格等于3
            if (outSum != MIDDLE_NODE_OUT_SUM) {
                return false;
            }
            // 必须有出度
            if (!hasOut) {
                return false;
            }

            // 3. 计算当前节点入度和 & 是否有入度
            int inSum = 0;
            boolean hasIn = false;
            for (int k = 0; k < nodeCount; k++) {
                int val = graph[k][i];
                inSum += val;
                if (val != 0) hasIn = true;
            }
            // 必须有入度
            if (!hasIn) {
                return false;
            }
        }
        return true;
    }

    /**
     * BFS 判断起点(0) 是否能到达终点(nodeCount-1)
     * 自动处理环，不会死循环
     */
    private boolean hasPathBFS(Integer[][] graph, int nodeCount) {
        boolean[] visited = new boolean[nodeCount];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        int endNode = nodeCount - 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 到达终点
            if (curr == endNode) {
                return true;
            }

            // 遍历所有邻接节点
            for (int j = 0; j < nodeCount; j++) {
                // 有边 且 未访问
                if (graph[curr][j] != 0 && !visited[j]) {
                    visited[j] = true;
                    queue.add(j);
                }
            }
        }

        // 无法到达终点
        return false;
    }
}
