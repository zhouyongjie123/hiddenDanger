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
@TableName("rectification_measure")
public class RectificationMeasure extends Entity {
    private Long hiddenRiskId;

    private String measureContent;

    private Long responsiblePersonId;

    private Date startTime;

    private Date completionTime;

    private String effectDescription;

    private Integer status;
}