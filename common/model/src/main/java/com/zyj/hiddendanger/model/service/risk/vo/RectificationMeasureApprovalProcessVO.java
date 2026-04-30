package com.zyj.hiddendanger.model.service.risk.vo;

import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalFlowProcessVO;
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
public class RectificationMeasureApprovalProcessVO implements Serializable {
    // 审批流程图信息
    private ApprovalFlowProcessVO approvalFlowProcessVO;

    // 整改措施信息
    private RectificationMeasureVO rectificationMeasureVO;

    @Serial
    private static final long serialVersionUID = -6129505493035466700L;
}
