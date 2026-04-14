package com.zyj.hiddendanger.model.domain;

import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdge;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowNode;

import java.util.List;

public class FlowProcess extends Entity {
    private String processId;

    private String businessId;

    private List<FlowNode> nodeList;

    private List<FlowEdge> edgeList;

    private String currentNodeId;  // 当前节点（分布式状态统一）
}
