package com.zyj.hiddendanger.model.service.flow.approval.domain.edge;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 审批流程边
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("approval_flow_edge")
@ToString(callSuper = true)
public class ApprovalFlowEdge extends FlowEdge<AbstractApprovalFlowEdgeEvent> {
}
