package com.zyj.hiddendanger.model.service.risk.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HiddenRiskReportDTO {
    @NotBlank(message = "隐患名称不能为空")
    private String name;

    @NotBlank(message = "隐患描述不能为空")
    private String description;

    @NotBlank(message = "隐患位置不能为空")
    private String location;

    @NotBlank(message = "隐患等级不能为空")
    private String riskLevelCode;

    @NotBlank(message = "隐患类型不能为空")
    private String riskTypeCode;

    @NotBlank(message = "责任部门不能为空")
    private String responsibleDepartmentId;

    @NotBlank(message = "责任人不能为空")
    private String responsiblePersonId;

    @NotBlank(message = "发现时间不能为空")
    private Date discoveryTime;

    @NotBlank(message = "整改期限不能为空")
    private Date rectificationDeadline;

    @NotBlank(message = "隐患状态不能为空")
    private String statusCode;

    @NotBlank(message = "隐患来源不能为空")
    private String sourceCode;
}
