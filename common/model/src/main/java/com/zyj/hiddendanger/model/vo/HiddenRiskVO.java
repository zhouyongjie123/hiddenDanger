package com.zyj.hiddendanger.model.vo;

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

    private Integer riskType;

    private Long responsibleDepartmentId;

    private Long responsiblePersonId;

    private Date discoveryTime;

    private Date rectificationDeadline;

    private Integer status;

    private Integer source;
}
