package com.zyj.hiddendanger.rpc.api.risk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HiddenRiskDepartmentStatisticResponse {
    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 部门负责人id
     */
    private String departmentLeaderId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 累计隐患数
     */
    private Long totalHiddenRiskCount;

    /**
     * 待整改的隐患数
     */
    private Long waitRectifyHiddenRiskCount;

    /**
     * 整改中的隐患数
     */
    private Long rectifyingHiddenRiskCount;

    /**
     * 待验收的隐患数
     */
    private Long waitAcceptanceHiddenRiskCount;

    /**
     * 已闭环的隐患数
     */
    private Long closedHiddenRiskCount;

    /**
     * 已撤销的隐患数
     */
    private Long canceledHiddenRiskCount;
}
