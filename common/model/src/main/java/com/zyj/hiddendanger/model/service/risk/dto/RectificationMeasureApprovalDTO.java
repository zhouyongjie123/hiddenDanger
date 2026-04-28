package com.zyj.hiddendanger.model.service.risk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RectificationMeasureApprovalDTO {
    /**
     * 要审批的整改措施id
     */
    private String rectificationMeasureId;

    /**
     * 审批意见
     */
    private String approvalMessage;
}
