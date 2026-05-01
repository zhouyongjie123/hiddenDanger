package com.zyj.hiddendanger.risk.service.impl;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.domain.HiddenRiskStatusStream;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.RejectApprovalEvent;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureApprovalDTO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskStatusStreamMapper;
import com.zyj.hiddendanger.risk.service.RectificationMeasureApprovalService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
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

    private final HiddenRiskMapper hiddenRiskMapper;

    private final HiddenRiskStatusStreamMapper hiddenRiskStatusStreamMapper;

    @Override
    public void approvalAccept(RectificationMeasureApprovalDTO dto) {
        String hiddenRiskId = dto.getRectificationMeasureId();
        String approvalMessage = dto.getApprovalMessage();
        RpcContext.getClientAttachment().setAttachment("userId", UserIdContextHolder.get());
        ApprovalResponse response = approvalFacadeService.approve(
                new AcceptApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
        if (response.isEnd()) {
            // 更改隐患状态
            HiddenRisk hiddenRisk = hiddenRiskMapper.selectById(hiddenRiskId);
            hiddenRisk.transition(HiddenRisk.RiskEvent.ACCEPT);
            hiddenRiskMapper.updateById(hiddenRisk);
            // 更新隐患状态流水
            HiddenRiskStatusStream hiddenRiskStatusStream = new HiddenRiskStatusStream().setHiddenRiskId(hiddenRiskId)
                                                                                        .setOperationType(
                                                                                                HiddenRisk.RiskEvent.ACCEPT);
            hiddenRiskStatusStreamMapper.insert(hiddenRiskStatusStream);
        }
    }

    @Override
    public void approvalReject(RectificationMeasureApprovalDTO dto) {
        String hiddenRiskId = dto.getRectificationMeasureId();
        String approvalMessage = dto.getApprovalMessage();
        RpcContext.getClientAttachment().setAttachment("userId", UserIdContextHolder.get());
        approvalFacadeService.approve(new RejectApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage));
    }
}
