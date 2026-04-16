package com.zyj.hiddendanger.model.service.flow.approval.domain.node;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.model.domain.ApprovalRecord;
import com.zyj.hiddendanger.model.domain.FlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 审批节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("approval_flow_node")
public class ApprovalFlowNode extends FlowNode {
    /**
     * 特殊的开始节点
     */
    public final static ApprovalFlowNode START = new ApprovalFlowNode();

    /**
     * 特殊的结束节点
     */
    public final static ApprovalFlowNode END = new ApprovalFlowNode();

    static {
        START.setId("-1");
        START.setProcessId("-2");
    }

    /**
     * 审批状态
     */
    private ApprovalStatusEnum status;

    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批记录
     */
    private List<ApprovalRecord> approvalRecords;
}
