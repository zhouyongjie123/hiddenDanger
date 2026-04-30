package com.zyj.hiddendanger.flow.service;

import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalFlowProcessVO;

public interface ApprovalFlowProcessService {
    boolean handleEvent(AbstractApprovalFlowEdgeEvent event);

    ApprovalFlowProcessVO getApprovalFlowProcessVOByBusinessId(String businessId);
}
