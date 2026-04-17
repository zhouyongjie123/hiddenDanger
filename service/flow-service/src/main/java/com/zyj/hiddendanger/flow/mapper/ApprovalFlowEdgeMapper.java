package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.ApprovalFlowEdge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalFlowEdgeMapper extends BaseMapper<ApprovalFlowEdge> {
    int insertBatch(List<ApprovalFlowEdge> list);
}




