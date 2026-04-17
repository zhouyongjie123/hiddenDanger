package com.zyj.hiddendanger.flow.mapper;

import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlowProcessMapper {
    FlowProcess<AbstractApprovalFlowEdgeEvent> getApprovalFlowProcess(String businessId);

    void saveFlowProcess(FlowProcess<?> flowProcess);
}