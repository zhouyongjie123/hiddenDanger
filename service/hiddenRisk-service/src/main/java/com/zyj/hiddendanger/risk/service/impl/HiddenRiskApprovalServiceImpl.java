package com.zyj.hiddendanger.risk.service.impl;

import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.model.service.flow.approval.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.RejectApprovalEvent;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;
import com.zyj.hiddendanger.risk.service.HiddenRiskApprovalService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiddenRiskApprovalServiceImpl implements HiddenRiskApprovalService {
    @Resource
    private IdGenerator<String> idGenerator;

    @RpcReference
    private ApprovalFacadeService approvalFacadeService;

    @Override
    public void approvalAccept(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
        approvalFacadeService.approve(new AcceptApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
    }

    @Override
    public void approvalReject(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
        approvalFacadeService.approve(new RejectApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
    }
}
