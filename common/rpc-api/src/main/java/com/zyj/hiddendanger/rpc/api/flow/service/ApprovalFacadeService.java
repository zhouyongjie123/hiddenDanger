package com.zyj.hiddendanger.rpc.api.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalFlowProcessVO;
import com.zyj.hiddendanger.rpc.api.flow.request.MyApprovalNodeRequest;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.response.RpcPageResult;

public interface ApprovalFacadeService {
    ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event);

    void createApprovalProcess(ApprovalFlowCreateDTO dto);

    /**
     * 根据审批人id查询所有的处理中业务id
     */
    RpcPageResult<String> getMyApprovalNode(MyApprovalNodeRequest request);

    /**
     * 根据业务id查询审批流程(用于展示)
     */
    ApprovalFlowProcessVO getApprovalFlowProcessVOByBusinessId(String businessId);
}
