package com.zyj.hiddendanger.rpc.api.flow.service.mock;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalFlowProcessVO;
import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.flow.request.MyApprovalNodeRequest;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import com.zyj.hiddendanger.rpc.response.RpcPageResult;

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
    public RpcPageResult<String> getMyApprovalNode(MyApprovalNodeRequest request) {
        return null;
    }

    @Override
    public ApprovalFlowProcessVO getApprovalFlowProcessVOByBusinessId(String businessId) {
        return null;
    }

}
