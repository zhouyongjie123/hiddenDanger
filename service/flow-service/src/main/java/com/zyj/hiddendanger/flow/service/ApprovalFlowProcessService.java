package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.flow.infrustructure.flow.approval.event.AbstractApprovalFlowEdgeEvent;

public interface ApprovalFlowProcessService {
    void handleEvent(AbstractApprovalFlowEdgeEvent event);
}
