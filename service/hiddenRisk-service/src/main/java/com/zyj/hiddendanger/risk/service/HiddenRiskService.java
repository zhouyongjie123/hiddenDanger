package com.zyj.hiddendanger.risk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.vo.HiddenRiskVO;

public interface HiddenRiskService extends IService<HiddenRisk> {
    Page<HiddenRiskVO> page(Page<HiddenRisk> page);
}
