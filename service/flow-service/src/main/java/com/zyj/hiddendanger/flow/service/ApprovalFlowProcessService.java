package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;

public interface ApprovalFlowProcessService {
    void handleEvent(AbstractApprovalFlowEdgeEvent event);
}
