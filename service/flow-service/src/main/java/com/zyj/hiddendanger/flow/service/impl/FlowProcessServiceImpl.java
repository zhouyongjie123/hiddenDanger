package com.zyj.hiddendanger.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.flow.mapper.FlowProcessMapper;
import com.zyj.hiddendanger.flow.service.FlowProcessService;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import org.springframework.stereotype.Service;

@Service
public class FlowProcessServiceImpl extends ServiceImpl<FlowProcessMapper, FlowProcess<? extends FlowEdgeEvent>>
        implements FlowProcessService {
}




