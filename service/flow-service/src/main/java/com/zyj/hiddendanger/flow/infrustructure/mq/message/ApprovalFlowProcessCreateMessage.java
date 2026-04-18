package com.zyj.hiddendanger.flow.infrustructure.mq.message;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.ApprovalFlowEdge;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ApprovalFlowProcessCreateMessage implements Serializable {
    private String flowProcessId;

    private List<ApprovalFlowNode> nodeList;

    private List<ApprovalFlowEdge> edgeList;

    @Serial
    private static final long serialVersionUID = 1L;
}
