package com.zyj.hiddendanger.model.service.auth.vo;

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

    private String riskLevel;

    private String riskType;

    private String responsibleDepartmentName;

    private String responsiblePersonName;

    private Date discoveryTime;

    private Date rectificationDeadline;

    private String status;

    private String source;
}
