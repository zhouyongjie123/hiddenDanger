package com.zyj.hiddendanger.model.service.flow.approval.event;

import java.util.ArrayList;
import java.util.List;

public class ApprovalFlowEdgeEventParser {
    public static final int ACCEPT_EVENT = 0b0001;

    public static final int REJECT_EVENT = 0b0010;

    public static List<Class<? extends AbstractApprovalFlowEdgeEvent>> getSupportedEventClass(Integer value) {
        List<Class<? extends AbstractApprovalFlowEdgeEvent>> result = new ArrayList<>();
        if ((value & ACCEPT_EVENT) != 0) {
            result.add(AcceptApprovalEvent.class);
        }
        if ((value & REJECT_EVENT) != 0){
            result.add(RejectApprovalEvent.class);
        }
        return result;
    }

    private ApprovalFlowEdgeEventParser() {
    }
}
