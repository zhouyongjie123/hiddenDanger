package com.zyj.hiddendanger.model.service.flow.approval.domain.node;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.model.domain.FlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审批节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("approval_flow_node")
public class ApprovalFlowNode extends FlowNode {
    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批状态
     */
    private ApprovalStatusEnum status;

    /**
     * 审批意见
     */
    private String approvalMessage;
}
