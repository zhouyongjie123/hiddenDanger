package com.zyj.hiddendanger.risk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.database.PageResult;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HiddenRiskMapper extends BaseMapper<HiddenRisk> {
    PageResult<HiddenRisk> selectPage(HiddenRiskPageQueryDTO dto);
}




