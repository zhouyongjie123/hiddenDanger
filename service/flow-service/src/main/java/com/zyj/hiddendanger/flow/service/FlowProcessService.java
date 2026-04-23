package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;

public interface FlowProcessService {
    void createApprovalProcess(ApprovalFlowCreateDTO dto);
}
