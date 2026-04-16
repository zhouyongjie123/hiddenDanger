package com.zyj.hiddendanger.model.service.flow.approval.dto;

import com.zyj.hiddendanger.model.service.flow.approval.graph.ApprovalFlowGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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
     * 节点信息列表
     */
    private List<ApprovalFlowNodeInfo> nodeInfoList;

    /**
     * 边信息列表
     */
    private List<ApprovalFlowEdgeInfo> edgeInfoList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public final static class ApprovalFlowNodeInfo {
        private String approverId;

        private String nodeIndex;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public final static class ApprovalFlowEdgeInfo {
        private String remark;
    }
}
