package com.zyj.hiddendanger.flow.infrustructure.flow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractFlowNode implements FlowNode {
    protected String id;

    protected Double x;

    protected Double y;
}
