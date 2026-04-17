package com.zyj.hiddendanger.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;

public interface FlowEdgeService extends IService<FlowEdge<? extends FlowEdgeEvent>> {
}
