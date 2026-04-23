package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.database.handler.IntegerArray2DTypeHandler;
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
public class FlowProcess<E extends FlowEdge<? extends FlowEdgeEvent>, N extends FlowNode> extends Entity {
    // 流程名字
    private String processName;

    // 业务id
    private String businessId;

    // 节点列表
    @TableField(exist = false)
    private List<N> nodeList;

    // 边列表
    @TableField(exist = false)
    private List<E> edgeList;

    @TableField(typeHandler = IntegerArray2DTypeHandler.class)
    private Integer[][] originalGraph;

    @TableField(exist = false)
    private Integer[][] minimizedGraph;

    public Integer[][] getMinimizedGraph() {
        if (this.minimizedGraph == null) {
            Integer[][] result = new Integer[originalGraph.length - 1][originalGraph.length - 1];
            for (int i = 1; i < originalGraph.length; i++) {
                System.arraycopy(originalGraph[i], 1, result[i - 1], 0, originalGraph.length - 1);
            }
            return result;
        }
        return this.minimizedGraph;
    }

    // 当前节点（分布式状态统一）
    private String currentNodeId;
}
