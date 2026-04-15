package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.database.handler.ClassListTypeHandler;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("flow_edge")
public class FlowEdge<E extends FlowEdgeEvent> extends Entity {
    /**
     * 关联流程的id
     */
    private String processId;

    // 边的源节点id
    private String sourceNodeId;

    // 边的目标节点id
    private String targetNodeId;

    // 支持的事件类型
    @TableField(typeHandler = ClassListTypeHandler.class)
    private List<Class<E>> supportedEventList;

    // 判断该边是否支持该事件
    public Boolean isSupportedEvent(E event) {
        return supportedEventList.stream().anyMatch(e -> e.isAssignableFrom(event.getClass()));
    }
}
