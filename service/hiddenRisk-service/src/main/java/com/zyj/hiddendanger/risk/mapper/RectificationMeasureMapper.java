package com.zyj.hiddendanger.risk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RectificationMeasureMapper extends BaseMapper<RectificationMeasure> {
    IPage<RectificationMeasureDTO> getMyRectificationMeasureList(Page<RectificationMeasure> page,);
}




