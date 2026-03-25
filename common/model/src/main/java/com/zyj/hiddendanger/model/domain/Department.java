package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("department")
public class Department extends Entity {
    private Long parentDepartmentId;

    private String departmentName;

    private String departmentPath;

    private Long leaderId;

    private Integer status;

    private Integer sortOrder;
}