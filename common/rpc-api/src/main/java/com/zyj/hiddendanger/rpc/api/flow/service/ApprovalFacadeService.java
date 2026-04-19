package com.zyj.hiddendanger.rpc.api.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;

public interface ApprovalFacadeService {
    void approve(AbstractApprovalFlowEdgeEvent event);
}
