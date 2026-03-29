package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;

public interface HiddenRiskService extends IService<HiddenRisk> {
    Page<HiddenRiskVO> page(Page<HiddenRisk> page);
}
