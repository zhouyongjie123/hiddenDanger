package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.FlowNode;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowEdgeEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FlowProcessMapper extends BaseMapper<FlowProcess<? extends FlowEdgeEvent, ? extends FlowNode>> {
    FlowProcess<AbstractApprovalFlowEdgeEvent, ApprovalFlowNode> getApprovalFlowProcess(String businessId);

    void saveFlowProcess(@Param("flowProcess") FlowProcess<? extends FlowEdgeEvent, ? extends FlowNode> flowProcess);
}