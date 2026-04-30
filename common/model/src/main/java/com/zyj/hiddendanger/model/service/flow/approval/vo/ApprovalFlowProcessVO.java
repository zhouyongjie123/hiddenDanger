package com.zyj.hiddendanger.model.service.flow.approval.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApprovalFlowProcessVO implements Serializable {
    // 流程id
    private String processId;

    // 流程名
    private String processName;

    // 原始图结构
    private Integer[][] originalGraph;

    // 节点列表
    private List<ApprovalFlowNodeVO> nodeList;

    // 当前节点id
    private String currentNodeId;

    @Serial
    private static final long serialVersionUID = 3957550906344183269L;
}
