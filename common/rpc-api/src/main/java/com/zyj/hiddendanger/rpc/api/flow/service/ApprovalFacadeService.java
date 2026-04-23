package com.zyj.hiddendanger.rpc.api.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;

public interface ApprovalFacadeService {
    ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event);
}
