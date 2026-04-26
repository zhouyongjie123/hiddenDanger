package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
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
    /**
     * 隐患id
     */
    private String hiddenRiskId;

    /**
     * 整改措施内容
     */
    private String measureContent;

    /**
     * 责任人id
     */
    private String responsiblePersonId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date completionTime;

    /**
     * 整改效果描述
     */
    private String effectDescription;

    public RectificationMeasureDTO toDTO() {
        return new RectificationMeasureDTO(
                this.id, this.hiddenRiskId, this.measureContent, this.responsiblePersonId, this.startTime,
                this.completionTime, this.effectDescription);
    }
}