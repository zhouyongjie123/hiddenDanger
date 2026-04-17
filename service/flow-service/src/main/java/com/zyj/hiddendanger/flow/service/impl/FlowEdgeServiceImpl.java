package com.zyj.hiddendanger.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.flow.mapper.FlowEdgeMapper;
import com.zyj.hiddendanger.flow.service.FlowEdgeService;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowEdgeServiceImpl extends ServiceImpl<FlowEdgeMapper, FlowEdge<? extends FlowEdgeEvent>> implements FlowEdgeService {
}
