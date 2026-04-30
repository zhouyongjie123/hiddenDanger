package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.status.ApprovalStatusEnum;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalRecordVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审批记录表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("approval_record")
public class ApprovalRecord extends Entity {
    /**
     * 审批节点id
     */
    private String approvalFlowNodeId;

    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批意见
     */
    private String approvalMessage;

    /**
     * 审批后的状态
     */
    private ApprovalStatusEnum status;

    public ApprovalRecordVO toVO(String approverName) {
        return new ApprovalRecordVO(this.getId(), this.approverId, approverName, this.approvalMessage, this.status);
    }
}
