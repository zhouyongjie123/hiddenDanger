package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlowProcessMapper<E extends FlowEdgeEvent> extends BaseMapper<FlowProcess<E>> {
    FlowProcess<AbstractApprovalFlowEdgeEvent> getApprovalFlowProcess(String businessId);
}
