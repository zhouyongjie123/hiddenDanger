package com.zyj.hiddendanger.model.service.flow.approval.dto;

import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import com.zyj.hiddendanger.model.validation.annotation.ApprovalFlowCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApprovalFlowCreate
public class ApprovalFlowCreateDTO implements Serializable {
    /**
     * 流程名称
     */
    @NotBlank(message = "流程名称不能为空")
    private String processName;

    /**
     * 业务ID
     */
    @NotBlank(message = "业务id不能为空")
    private String businessId;

    /**
     * 流程图
     */
    @NotNull(message = "流程图不能为空")
    private ApprovalFlowGraph graph;


    /**
     * 审批人id列表
     */
    @NotEmpty(message = "审批人id列表不能为空")
    private String[] approverIds;

    @Serial
    private static final long serialVersionUID = 1L;
}
