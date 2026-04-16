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
}
