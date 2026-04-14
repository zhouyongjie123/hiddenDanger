package com.zyj.hiddendanger.flow.infrustructure;

import jakarta.validation.constraints.NotNull;

public interface FlowNode {
    @NotNull
    String getId();

    @NotNull
    Double getX();

    @NotNull
    Double getY();
}
