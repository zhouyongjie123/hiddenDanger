package com.zyj.hiddendanger.rpc.api.flow.service.mock;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;

@RpcMockService
public class ApprovalFacadeServiceMock implements ApprovalFacadeService {
    @Override
    public ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event) {
        return new ApprovalResponse(Boolean.FALSE);
    }
}
