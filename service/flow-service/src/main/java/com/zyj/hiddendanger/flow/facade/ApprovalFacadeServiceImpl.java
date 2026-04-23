package com.zyj.hiddendanger.flow.facade;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

@Facade
@Service
@RequiredArgsConstructor
@DubboService
public class ApprovalFacadeServiceImpl implements ApprovalFacadeService {
    private final ApprovalFlowProcessService approvalFlowProcessService;

    @Override
    @Idempotent(idempotentKey = "#event.getEventId")
    public ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event) {
        String userId = RpcContext.getServerAttachment().getAttachment("userId");
        UserIdContextHolder.set(userId);
        return new ApprovalResponse(approvalFlowProcessService.handleEvent(event));
    }
}
