package com.zyj.hiddendanger.flow.infrustructure;

public interface FlowProcess {
    void onEvent(FlowEdgeEvent event);
}
