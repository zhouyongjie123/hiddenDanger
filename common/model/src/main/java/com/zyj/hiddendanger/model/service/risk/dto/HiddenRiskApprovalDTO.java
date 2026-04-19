package com.zyj.hiddendanger.model.service.risk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HiddenRiskApprovalDTO {
    /**
     * 要审批的隐患id
     */
    private String hiddenRiskId;

    /**
     * 审批意见
     */
    private String approvalMessage;
}
