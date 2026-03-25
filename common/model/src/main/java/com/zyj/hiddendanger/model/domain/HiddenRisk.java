package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("hidden_risk")
public class HiddenRisk  extends Entity {
    private String name;

    private String description;

    private String location;

    private Integer riskLevel;

    private Integer riskType;

    private Long responsibleDepartmentId;

    private Long responsiblePersonId;

    private Date discoveryTime;

    private Date rectificationDeadline;

    private Integer status;

    private Integer source;
}