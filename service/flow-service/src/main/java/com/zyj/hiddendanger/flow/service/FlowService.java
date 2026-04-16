package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;

public interface FlowService {
    void createApprovalProcess(ApprovalFlowCreateDTO dto);
}
