package com.zyj.hiddendanger.model.service.risk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RectificationMeasureVO implements Serializable {
    /**
     * 整改措施id
     */
    private String rectificationMeasureId;

    /**
     * 隐患id
     */
    private String hiddenRiskId;

    /**
     * 隐患名称
     */
    private String hiddenRiskName;

    /**
     * 整改措施内容
     */
    private String measureContent;

    /**
     * 责任人id
     */
    private String responsiblePersonId;

    /**
     * 责任人姓名
     */
    private String responsiblePersonName;

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
}
