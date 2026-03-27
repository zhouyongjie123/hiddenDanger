package com.zyj.hiddendanger.risk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.vo.HiddenRiskVO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HiddenRiskServiceImpl extends ServiceImpl<HiddenRiskMapper, HiddenRisk>
        implements HiddenRiskService {

    private final HiddenRiskMapper hiddenRiskMapper;

    @DubboReference
    private DepartmentFacadeService departmentFacadeService;

    @DubboReference
    private UserFacadeService userFacadeService;

    @Override
    public Page<HiddenRiskVO> page(Page<HiddenRisk> page) {
        Page<HiddenRisk> hiddenRiskPage = hiddenRiskMapper.selectPage(page, null);
        // 优化:查询完成之后放到map中,之后如果有相同的查询,就先从map中取
        // todo 改成在redis中缓存结果
        final Map<String, String> map = new HashMap<>();
        List<HiddenRiskVO> list = hiddenRiskPage.getRecords().stream().map(record -> {
            // 查询部门名字
            String departmentName = map.get(record.getResponsibleDepartmentId());
            if (departmentName == null) {
                String departmentNameById = departmentFacadeService.getDepartmentNameById(
                        record.getResponsibleDepartmentId());
                map.put(record.getResponsibleDepartmentId(), departmentNameById);
                departmentName = departmentNameById;
            }

            // 查询人名
            String realName = map.get(record.getResponsiblePersonId());
            if (realName == null) {
                String realNameById = userFacadeService.getRealNameById(record.getResponsiblePersonId());
                map.put(record.getResponsiblePersonId(), realNameById);
                realName = realNameById;
            }

            return record.toHiddenRiskVO(departmentName, realName);
        }).toList();
        Page<HiddenRiskVO> hiddenRiskVOPage = new Page<>();
        BeanUtil.copyProperties(hiddenRiskPage, hiddenRiskVOPage);
        hiddenRiskVOPage.setRecords(list);
        return hiddenRiskVOPage;
    }
}




