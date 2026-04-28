package com.zyj.hiddendanger.risk.service.impl;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.RejectApprovalEvent;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureApprovalDTO;
import com.zyj.hiddendanger.risk.service.RectificationMeasureApprovalService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RectificationMeasureApprovalServiceImpl implements RectificationMeasureApprovalService {
    @Resource
    private IdGenerator<String> idGenerator;

    @RpcReference
    private ApprovalFacadeService approvalFacadeService;

    @Override
    public void approvalAccept(RectificationMeasureApprovalDTO dto) {
        String hiddenRiskId = dto.getRectificationMeasureId();
        String approvalMessage = dto.getApprovalMessage();
        RpcContext.getClientAttachment().setAttachment("userId", UserIdContextHolder.get());
        approvalFacadeService.approve(new AcceptApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
    }

    @Override
    public void approvalReject(RectificationMeasureApprovalDTO dto) {
        String hiddenRiskId = dto.getRectificationMeasureId();
        String approvalMessage = dto.getApprovalMessage();
        RpcContext.getClientAttachment().setAttachment("userId", UserIdContextHolder.get());
        approvalFacadeService.approve(new RejectApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
    }
}
