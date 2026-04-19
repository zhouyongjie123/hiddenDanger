package com.zyj.hiddendanger.model.service.flow.approval.event;

import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractApprovalFlowEdgeEvent implements FlowEdgeEvent {
    protected String businessId;

    protected String eventId;

    // 审批意见
    protected String approvalMessage;

    @Serial
    private static final long serialVersionUID = 1L;
}
