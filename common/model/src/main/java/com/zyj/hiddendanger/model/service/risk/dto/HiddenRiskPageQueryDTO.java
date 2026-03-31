package com.zyj.hiddendanger.model.service.risk.dto;

import com.zyj.hiddendanger.database.BasePageQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HiddenRiskPageQueryDTO extends BasePageQueryDTO {
    private String departmentId;

    private String riskLevel;

    private String riskType;

    private String status;

    private String source;

    /**
     * name模糊查询
     */
    private String name;

    /**
     * discoveryTime开始时间
     */
    private Date beginDiscoveryTime;

    /**
     * discoveryTime结束时间
     */
    private Date endDiscoveryTime;

    /**
     * rectificationDeadline开始时间
     */
    private Date beginRectificationDeadline;

    /**
     * rectificationDeadline结束时间
     */
    private Date endRectificationDeadline;
}
