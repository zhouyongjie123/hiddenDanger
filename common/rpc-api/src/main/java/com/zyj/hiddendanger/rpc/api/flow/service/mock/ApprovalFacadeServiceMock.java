package com.zyj.hiddendanger.rpc.api.flow.service.mock;

import com.zyj.hiddendanger.database.PageResult;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;

@RpcMockService
public class ApprovalFacadeServiceMock implements ApprovalFacadeService {
    @Override
    public ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event) {
        return new ApprovalResponse(Boolean.FALSE);
    }

    @Override
    public void createApprovalProcess(ApprovalFlowCreateDTO dto) {
    }

    @Override
    public PageResult<String> getMyApprovalProcess(Long current, Long pageSize) {
        return null;
    }
}
