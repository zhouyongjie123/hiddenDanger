package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalFlowNodeMapper extends BaseMapper<ApprovalFlowNode> {
    int insertBatch(List<ApprovalFlowNode> list);
}




