package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.auth.service.HiddenRiskService;
import com.zyj.hiddendanger.database.util.PageUtil;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HiddenRiskServiceImpl extends ServiceImpl<HiddenRiskMapper, HiddenRisk>
        implements HiddenRiskService {

    private final HiddenRiskMapper hiddenRiskMapper;

    @RpcReference
    private DepartmentFacadeService departmentFacadeService;

    @RpcReference
    private UserFacadeService userFacadeService;

    @Resource
    private Cache<String, String> departmentNameCache;

    @Resource
    private Cache<String, String> userNameCache;

    private LambdaQueryWrapper<HiddenRisk> getPageQueryWrapper(HiddenRiskPageQueryDTO dto) {
        String departmentId = dto.getDepartmentId();
        String riskLevel = dto.getRiskLevel();
        String riskType = dto.getRiskType();
        String status = dto.getStatus();
        String source = dto.getSource();
        String name = dto.getName();
        Date beginDiscoveryTime = dto.getBeginDiscoveryTime();
        Date endDiscoveryTime = dto.getEndDiscoveryTime();
        Date beginRectificationDeadline = dto.getBeginRectificationDeadline();
        Date endRectificationDeadline = dto.getEndRectificationDeadline();
        return new LambdaQueryWrapper<HiddenRisk>()
                .eq(StringUtils.hasLength(departmentId), HiddenRisk::getResponsibleDepartmentId, departmentId)
                .eq( StringUtils.hasLength(riskLevel), HiddenRisk::getRiskLevel, riskLevel)
                .eq(StringUtils.hasLength(riskType), HiddenRisk::getRiskType, riskType)
                .eq(StringUtils.hasLength(status), HiddenRisk::getStatus, status)
                .eq(StringUtils.hasLength(source), HiddenRisk::getSource, source)
                .like(StringUtils.hasLength(name), HiddenRisk::getName, name)
                .ge(beginDiscoveryTime != null, HiddenRisk::getDiscoveryTime, beginDiscoveryTime)
                .le(endDiscoveryTime != null, HiddenRisk::getDiscoveryTime, endDiscoveryTime)
                .ge(
                        beginRectificationDeadline != null, HiddenRisk::getRectificationDeadline,
                        beginRectificationDeadline)
                .le(
                        endRectificationDeadline != null, HiddenRisk::getRectificationDeadline,
                        endRectificationDeadline);
    }

    @Override
    public Page<HiddenRiskVO> page(HiddenRiskPageQueryDTO dto) {
        Page<HiddenRisk> hiddenRiskPage = hiddenRiskMapper.selectPage(
                Page.of(dto.getCurrent(), dto.getPageSize()), getPageQueryWrapper(dto));
        // 优化:本地和远程缓存部门名,用户名
        List<HiddenRiskVO> list = hiddenRiskPage.getRecords().stream().map(record -> {
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
        return PageUtil.pageConvert(hiddenRiskPage, list);
    }
}




