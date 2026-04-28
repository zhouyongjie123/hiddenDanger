package com.zyj.hiddendanger.model.service.risk.vo;

import com.zyj.hiddendanger.model.domain.HiddenRisk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HiddenRiskVO {
    private String name;

    private String description;

    private String location;

    private HiddenRisk.RiskLevel riskLevel;

    private HiddenRisk.RiskType riskType;

    private String responsibleDepartmentName;

    private String responsiblePersonName;

    private Date discoveryTime;

    private Date rectificationDeadline;

    private HiddenRisk.RiskStatus status;

    private HiddenRisk.RiskSource source;

    /**
     * 是否整改
     */
    private Boolean isRectify;
}
