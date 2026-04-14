package com.zyj.hiddendanger.flow.service.impl;

import com.zyj.hiddendanger.flow.infrustructure.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import org.springframework.stereotype.Service;

@Service
public class ApprovalFlowProcessServiceImpl implements ApprovalFlowProcessService {
    @Override
    public void handleEvent(AbstractApprovalFlowEdgeEvent event) {

    }
}
