package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.FlowEdge;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlowEdgeMapper extends BaseMapper<FlowEdge<? extends FlowEdgeEvent>> {
}
