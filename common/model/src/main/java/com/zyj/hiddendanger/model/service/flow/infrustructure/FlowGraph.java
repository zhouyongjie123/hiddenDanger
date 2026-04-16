package com.zyj.hiddendanger.model.service.flow.infrustructure;

import com.zyj.hiddendanger.model.domain.FlowProcess;

public interface FlowGraph<E extends FlowEdgeEvent> {
    /*
     * 判断流程图是否合法
     */
    Boolean isLegal();

    /*
     * 获取原始图
     */
    Integer[][] getOriginalGraph();

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
     * 获取当前节点id
     */
    String getCurrentNodeId();
}
