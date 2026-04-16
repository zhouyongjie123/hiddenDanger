package com.zyj.hiddendanger.model.service.flow.infrustructure;

import com.zyj.hiddendanger.model.domain.FlowProcess;

public interface FlowGraph<E extends FlowEdgeEvent> {
    /*
     * 获取原始图
     */
    Integer[][] getOriginalGraph();

    /**
     * 获取维度
     */
    Integer getDimension();

    /**
     * 获取流程名
     */
    String getProcessName();

    /**
     * 获取业务id
     */
    String getBusinessId();

    /**
     * 转换为流程对象
     */
    FlowProcess<E> convertToFlowProcess();

    /**
     * 判断 Integer[][] 二维数组是否为合法矩阵
     */
    default boolean isMatrix(Integer[][] graph) {
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
}
