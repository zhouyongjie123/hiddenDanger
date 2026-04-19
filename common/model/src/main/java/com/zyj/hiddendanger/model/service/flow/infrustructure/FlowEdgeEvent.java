package com.zyj.hiddendanger.model.service.flow.infrustructure;

import java.io.Serializable;

public interface FlowEdgeEvent extends Serializable {
    // 业务号
    String getBusinessId();

    // 事件Id用于幂等
    String getEventId();
}
