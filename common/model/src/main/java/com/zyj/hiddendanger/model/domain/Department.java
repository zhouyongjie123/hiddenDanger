package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("department")
public class Department extends Entity {
    /**
     * 父部门ID，顶级部门为NULL
     */
    private String parentDepartmentId;

    /**
     * 部门名
     */
    private String departmentName;

    /**
     * 部门层级路径，如 /1/2/3，便于快速查询子树
     */
    private String departmentPath;

    /**
     * 部门负责人ID
     */
    private String leaderId;

    /**
     * 部门状态：1-启用，0-禁用
     */
    @EnumValue
    private Status status;


    /**
     * 同级排序序号
     */
    private Integer sortOrder;

    @Getter
    @AllArgsConstructor
    public enum Status {
        ENABLED("1", "启用"),
        DISABLED("0", "禁用");

        @EnumValue
        private final String code;

        private final String name;
    }

    public DepartmentInfoVO toDepartmentInfoVO(String leaderName) {
        return new DepartmentInfoVO()
                .setId(this.getId())
                .setDepartmentName(this.getDepartmentName())
                .setDepartmentPath(this.getDepartmentPath())
                .setLeaderName(leaderName)
                .setStatus(this.getStatus().getName())
                .setSortOrder(this.getSortOrder());
    }
}