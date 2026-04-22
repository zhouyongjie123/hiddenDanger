package com.zyj.hiddendanger.rpc.api.flow.service.mock;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;

@RpcMockService
public class ApprovalFacadeServiceMock implements ApprovalFacadeService {
    @Override
    public void approve(AbstractApprovalFlowEdgeEvent event) {
    }
}
