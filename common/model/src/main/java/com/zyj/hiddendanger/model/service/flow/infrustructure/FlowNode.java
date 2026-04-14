package com.zyj.hiddendanger.model.service.flow.infrustructure;

import jakarta.validation.constraints.NotNull;

public interface FlowNode {
    @NotNull
    String getId();

    @NotNull
    Double getX();

    @NotNull
    Double getY();
}
