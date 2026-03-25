package com.zyj.hiddendanger.risk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.vo.HiddenRiskVO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HiddenRiskServiceImpl extends ServiceImpl<HiddenRiskMapper, HiddenRisk>
        implements HiddenRiskService {

    private final HiddenRiskMapper hiddenRiskMapper;

    @Override
    public Page<HiddenRiskVO> page(Page<HiddenRisk> page) {
        Page<HiddenRisk> hiddenRiskPage = hiddenRiskMapper.selectPage(page, null);
        List<HiddenRiskVO> list = hiddenRiskPage.getRecords().stream().map(record -> {
            // todo 这里要做真正的查询
            return record.toHiddenRiskVO("unknown", "unknown");
        }).toList();
        Page<HiddenRiskVO> hiddenRiskVOPage = new Page<>();
        BeanUtil.copyProperties(hiddenRiskPage, hiddenRiskVOPage);
        hiddenRiskVOPage.setRecords(list);
        return hiddenRiskVOPage;
    }
}




