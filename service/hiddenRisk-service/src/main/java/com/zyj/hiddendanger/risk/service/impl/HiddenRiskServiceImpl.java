package com.zyj.hiddendanger.risk.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.DatabaseExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.web.util.PageUtil;
import com.zyj.hiddendanger.model.domain.HiddenRisk;
import com.zyj.hiddendanger.model.service.risk.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskReportDTO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.service.HiddenRiskService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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


    private LambdaQueryWrapper<HiddenRisk> getHiddenRiskQueryWrapper(HiddenRiskPageQueryDTO dto) {
        return new LambdaQueryWrapper<HiddenRisk>()
                .eq(
                        StringUtils.hasText(dto.getDepartmentId()), HiddenRisk::getResponsibleDepartmentId,
                        dto.getDepartmentId())
                .eq(StringUtils.hasText(dto.getRiskType()), HiddenRisk::getRiskType, dto.getRiskType())
                .eq(StringUtils.hasText(dto.getRiskLevel()), HiddenRisk::getRiskLevel, dto.getRiskLevel())
                .eq(StringUtils.hasText(dto.getStatus()), HiddenRisk::getStatus, dto.getStatus())
                .eq(StringUtils.hasText(dto.getSource()), HiddenRisk::getSource, dto.getSource())
                .like(StringUtils.hasText(dto.getName()), HiddenRisk::getName, dto.getName())
                .ge(
                        dto.getBeginDiscoveryTime() != null, HiddenRisk::getDiscoveryTime,
                        dto.getBeginDiscoveryTime())
                .le(dto.getEndDiscoveryTime() != null, HiddenRisk::getDiscoveryTime, dto.getEndDiscoveryTime())
                .ge(
                        dto.getBeginRectificationDeadline() != null, HiddenRisk::getRectificationDeadline,
                        dto.getBeginRectificationDeadline())
                .le(
                        dto.getEndRectificationDeadline() != null, HiddenRisk::getRectificationDeadline,
                        dto.getEndRectificationDeadline());
    }

    @Override
    public Page<HiddenRiskVO> page(HiddenRiskPageQueryDTO dto) {
        Page<HiddenRisk> hiddenRiskPage = hiddenRiskMapper.selectPage(
                Page.of(dto.getCurrent(), dto.getPageSize()), getHiddenRiskQueryWrapper(dto));
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
        return PageUtil.convert2Page(hiddenRiskPage, list);
    }

    @Override
    public HiddenRiskVO report(HiddenRiskReportDTO hiddenRiskReportDTO) {
        HiddenRisk hiddenRisk = new HiddenRisk().setName(hiddenRiskReportDTO.getName())
                                                .setDescription(hiddenRiskReportDTO.getDescription())
                                                .setLocation(hiddenRiskReportDTO.getLocation())
                                                .setRiskLevel(hiddenRiskReportDTO.getRiskLevel())
                                                .setRiskType(
                                                        hiddenRiskReportDTO.getRiskType())
                                                .setResponsibleDepartmentId(
                                                        hiddenRiskReportDTO.getResponsibleDepartmentId())
                                                .setResponsiblePersonId(hiddenRiskReportDTO.getResponsiblePersonId())
                                                .setDiscoveryTime(hiddenRiskReportDTO.getDiscoveryTime())
                                                .setRectificationDeadline(
                                                        hiddenRiskReportDTO.getRectificationDeadline())
                                                .setStatus(HiddenRisk.RiskStatus.WAIT_RECTIFY)
                                                .setSource(
                                                        hiddenRiskReportDTO.getSource());
        ThrowUtil.throwIf(
                hiddenRiskMapper.insert(hiddenRisk) != 1,
                () -> new SystemException(DatabaseExceptionCode.INSERT_ERROR));
        String responsiblePersonName = userNameCache.get(hiddenRisk.getResponsiblePersonId());
        if (!StringUtils.hasText(responsiblePersonName)) {
            responsiblePersonName = userFacadeService.getRealNameById(hiddenRisk.getResponsiblePersonId());
            userNameCache.put(hiddenRisk.getResponsiblePersonId(), responsiblePersonName);
        }
        String responsibleDepartmentName = departmentNameCache.get(hiddenRisk.getResponsibleDepartmentId());
        if (!StringUtils.hasText(responsibleDepartmentName)) {
            responsibleDepartmentName = departmentFacadeService.getDepartmentNameById(
                    hiddenRisk.getResponsibleDepartmentId());
            departmentNameCache.put(hiddenRisk.getResponsibleDepartmentId(), responsibleDepartmentName);
        }
        return hiddenRisk.toHiddenRiskVO(responsiblePersonName, responsibleDepartmentName);
    }
}




