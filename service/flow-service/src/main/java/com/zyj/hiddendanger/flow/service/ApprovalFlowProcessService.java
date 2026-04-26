package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;

public interface ApprovalFlowProcessService {
    boolean handleEvent(AbstractApprovalFlowEdgeEvent event);
}
