package com.zyj.hiddendanger.rpc.api.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;

import java.util.List;

public interface ApprovalFacadeService {
    ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event);

    void createApprovalProcess(ApprovalFlowCreateDTO dto);

    /**
     * 根据审批人id查询所有的业务id
     */
    List<String> getMyApprovalProcess(String approverId);
}
