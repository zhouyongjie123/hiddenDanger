package com.zyj.hiddendanger.risk.service.impl;

import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiddenRiskApprovalServiceImpl implements HiddenRiskApprovalService {
    @Override
    public void approvalAccept(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
    }

    @Override
    public void approvalReject(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
    }
}
