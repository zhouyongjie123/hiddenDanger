package com.zyj.hiddendanger.model.service.flow.approval.vo;

import com.zyj.hiddendanger.model.service.flow.approval.domain.node.status.ApprovalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApprovalFlowNodeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -69607207671124842L;

    /**
     * 节点id
     */
    private String approvalFlowNodeId;

    /**
     * 审批状态
     */
    private ApprovalStatusEnum status;

    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批人姓名
     */
    private String approverName;

    /**
     * 流程id
     */
    private String processId;

    /**
     * 审批记录
     */
    private List<ApprovalRecordVO> approvalRecords;
}
