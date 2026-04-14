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
@TableName("flow_edge")
public class FlowEdge<E extends FlowEdgeEvent> extends Entity {
    // 边的源节点id
    private String sourceNodeId;

    // 边的目标节点id
    private String targetNodeId;

    // 支持的事件
    private List<E> events;

    // 判断该边是否支持该事件
    public Boolean isSupportedEvent(E event) {
        return events.stream().anyMatch(e -> e.equals(event));
    }
}
