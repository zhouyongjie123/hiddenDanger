package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("flow_process")
@Getter
@Setter
@ToString(callSuper = true)
public class FlowProcess<E extends FlowEdgeEvent> extends Entity {
    // 流程名字
    private String processName;

    // 业务id
    private String businessId;

    // 节点列表
    private List<? extends FlowNode> nodeList;

    // 边列表
    private List<? extends FlowEdge<? extends E>> edgeList;

    // 当前节点（分布式状态统一）
    private String currentNodeId;
}
