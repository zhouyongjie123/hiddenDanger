package com.zyj.hiddendanger.risk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;

public interface HiddenRiskService extends IService<HiddenRisk> {
    Page<HiddenRiskVO> page(HiddenRiskPageQueryDTO hiddenRiskPageQueryDTO);
}
