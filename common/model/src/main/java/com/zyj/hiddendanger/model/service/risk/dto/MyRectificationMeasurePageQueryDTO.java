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
public class MyRectificationMeasurePageQueryDTO extends BasePageQueryDTO {
    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date completionTime;
}
