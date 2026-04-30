package com.zyj.hiddendanger.model.service.flow.approval.vo;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.status.ApprovalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApprovalRecordVO implements Serializable {
    /**
     * 审批节点id
     */
    private String approvalFlowNodeId;

    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批人名称
     */
    private String approverName;

    /**
     * 审批意见
     */
    private String approvalMessage;

    /**
     * 审批后的状态
     */
    private ApprovalStatusEnum status;

    @Serial
    private static final long serialVersionUID = 2440346864650632724L;
}
