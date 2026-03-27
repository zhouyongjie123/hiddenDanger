package com.zyj.hiddendanger.risk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.vo.HiddenRiskVO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HiddenRiskServiceImpl extends ServiceImpl<HiddenRiskMapper, HiddenRisk>
        implements HiddenRiskService {

    private final HiddenRiskMapper hiddenRiskMapper;

    @DubboReference
    private DepartmentFacadeService departmentFacadeService;

    @DubboReference
    private UserFacadeService userFacadeService;

    @Resource
    private Cache<String, String> departmentNameCache;

    @Resource
    private Cache<String, String> userNameCache;

    @Override
    public Page<HiddenRiskVO> page(Page<HiddenRisk> page) {
        Page<HiddenRisk> hiddenRiskPage = hiddenRiskMapper.selectPage(page, null);
        // 优化:本地和远程缓存部门名,用户名
        List<HiddenRisk> records = hiddenRiskPage.getRecords();
        List<HiddenRiskVO> list = records.stream().map(record -> {
            // 查询部门名字
            String departmentName = departmentNameCache.get(record.getResponsibleDepartmentId());
            if (departmentName == null) {
                String departmentNameById = departmentFacadeService.getDepartmentNameById(
                        record.getResponsibleDepartmentId());
                departmentNameCache.put(record.getResponsibleDepartmentId(), departmentNameById);
                departmentName = departmentNameById;
            }

            // 查询人名
            String realName = userNameCache.get(record.getResponsiblePersonId());
            if (realName == null) {
                String realNameById = userFacadeService.getRealNameById(record.getResponsiblePersonId());
                userNameCache.put(record.getResponsiblePersonId(), realNameById);
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




