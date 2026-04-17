package com.zyj.hiddendanger.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;

public interface FlowProcessService extends IService<FlowProcess<? extends FlowEdgeEvent>> {
}
