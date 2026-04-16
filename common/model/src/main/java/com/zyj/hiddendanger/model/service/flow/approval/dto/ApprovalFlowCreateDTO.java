package com.zyj.hiddendanger.model.service.flow.approval.dto;

import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
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
    private String processName;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 流程图
     */
    private ApprovalFlowGraph graph;


    /**
     * 审批人id列表
     */
    private String[] approverIds;
}
