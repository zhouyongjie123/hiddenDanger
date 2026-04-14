package com.zyj.hiddendanger.flow.infrustructure.approval.event;

import com.zyj.hiddendanger.flow.infrustructure.FlowEdgeEvent;
import com.zyj.hiddendanger.flow.infrustructure.FlowNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcceptEvent implements FlowEdgeEvent {
    private FlowNode sourceNode;

    private String businessId;
}
