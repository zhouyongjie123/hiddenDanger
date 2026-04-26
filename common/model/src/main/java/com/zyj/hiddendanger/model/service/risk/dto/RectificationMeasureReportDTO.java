package com.zyj.hiddendanger.model.service.risk.dto;

import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RectificationMeasureReportDTO {
    /**
     * 隐患id
     */
    @NotBlank(message = "隐患id不能为空")
    private String hiddenRiskId;

    /**
     * 整改措施内容
     */
    @NotBlank(message = "整改措施内容不能为空")
    private String measureContent;

    /**
     * 责任人id
     */
    @NotBlank(message = "责任人id不能为空")
    private String responsiblePersonId;

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空")
    private Date startTime;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空")
    private Date completionTime;

    /**
     * 整改效果描述
     */
    private String effectDescription;

    @NotNull(message = "流程信息不能为空")
    private ApprovalFlowCreateDTO approvalFlowCreateDTO;
}
