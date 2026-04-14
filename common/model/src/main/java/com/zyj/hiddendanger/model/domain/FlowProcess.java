package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("flow_process")
public class FlowProcess<E extends FlowEdgeEvent> extends Entity {
    // 流程名字
    private String processName;

    // 业务id
    private String businessId;

    // 节点列表
    private List<FlowNode> nodeList;

    // 边列表
    private List<FlowEdge<E>> edgeList;

    // 当前节点（分布式状态统一）
    private String currentNodeId;
}
