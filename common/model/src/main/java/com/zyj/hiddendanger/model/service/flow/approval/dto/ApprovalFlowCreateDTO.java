package com.zyj.hiddendanger.model.service.flow.approval.dto;

import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApprovalFlowCreateDTO {
    /**
     * 流程名称
     */
    @NotNull(message = "流程名称不能为空")
    private String processName;

    /**
     * 业务ID
     */
    @NotNull(message = "业务id不能为空")
    private String businessId;

    /**
     * 流程图
     */
    @NotNull(message = "流程图不能为空")
    private ApprovalFlowGraph graph;


    /**
     * 审批人id列表
     */
    @NotNull(message = "审批人id列表不能为空")
    private String[] approverIds;
}
