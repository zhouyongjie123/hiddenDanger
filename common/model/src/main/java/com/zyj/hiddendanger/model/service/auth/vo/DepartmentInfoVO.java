package com.zyj.hiddendanger.model.service.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DepartmentInfoVO {
    /**
     * 部门id
     */
    private String id;

    /**
     * 父部门名字，顶级部门为无
     */
    private String parentDepartmentName;

    /**
     * 部门名
     */
    private String departmentName;

    /**
     * 部门层级路径，如 /1/2/3，便于快速查询子树
     */
    private String departmentPath;

    /**
     * 部门负责人名字
     */
    private String leaderName;

    /**
     * 部门状态
     */
    private String status;

    /**
     * 同级排序序号
     */
    private Integer sortOrder;
}
