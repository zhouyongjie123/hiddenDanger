package com.zyj.hiddendanger.flow.infrustructure.flow;

import jakarta.validation.constraints.NotNull;

public interface FlowNode {
    @NotNull
    String getId();

    @NotNull
    Double getX();

    @NotNull
    Double getY();
}
