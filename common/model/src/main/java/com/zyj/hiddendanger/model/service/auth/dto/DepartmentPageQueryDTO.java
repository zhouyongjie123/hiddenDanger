package com.zyj.hiddendanger.model.service.auth.dto;

import com.zyj.hiddendanger.database.BasePageQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DepartmentPageQueryDTO extends BasePageQueryDTO {
    private String name;

    /**
     * 部门负责人ID
     */
    private String leaderId;

    /**
     * 部门状态：1-启用，0-禁用
     */
    private String status;
}
