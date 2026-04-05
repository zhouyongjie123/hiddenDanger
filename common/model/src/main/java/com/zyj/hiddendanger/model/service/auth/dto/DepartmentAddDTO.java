package com.zyj.hiddendanger.model.service.auth.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zyj.hiddendanger.model.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DepartmentAddDTO {
    /**
     * 父部门ID，顶级部门为NULL
     */
    private String parentDepartmentId;

    /**
     * 部门名
     */
    private String departmentName;

    /**
     * 部门负责人ID
     */
    private String leaderId;
}
