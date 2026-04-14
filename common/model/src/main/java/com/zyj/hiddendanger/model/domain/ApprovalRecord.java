package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.flow.approval.enums.ApprovalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("approval_record")
public class ApprovalRecord extends Entity {
    /**
     * 审批人id
     */
    private String approvalId;

    /**
     * 审批意见
     */
    private String approvalMessage;

    /**
     * 顺序
     */
    private String order;

    /**
     * 审批后的状态
     */
    private ApprovalStatusEnum status;
}
