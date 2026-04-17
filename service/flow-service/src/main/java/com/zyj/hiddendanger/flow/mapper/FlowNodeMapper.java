package com.zyj.hiddendanger.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.FlowNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlowNodeMapper extends BaseMapper<FlowNode> {
    int insertBatch(List<? extends FlowNode> list);
}




