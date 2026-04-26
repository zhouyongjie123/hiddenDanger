package com.zyj.hiddendanger.risk.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.DatabaseExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.dto.MyRectificationMeasurePageQueryDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;
import com.zyj.hiddendanger.risk.mapper.HiddenRiskMapper;
import com.zyj.hiddendanger.risk.mapper.RectificationMeasureMapper;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;
import com.zyj.hiddendanger.rpc.api.flow.request.MyApprovalNodeRequest;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import com.zyj.hiddendanger.rpc.response.RpcPageResult;
import com.zyj.hiddendanger.web.util.PageUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RectificationMeasureServiceImpl extends ServiceImpl<RectificationMeasureMapper, RectificationMeasure> implements RectificationMeasureService {
    private final RectificationMeasureMapper rectificationMeasureMapper;

    private final HiddenRiskMapper hiddenRiskMapper;

    @RpcReference
    private UserFacadeService userFacadeService;

    @RpcReference
    private ApprovalFacadeService approvalFacadeService;

    @Resource
    private Cache<String, String> userNameCache;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(RectificationMeasureReportDTO dto) {
        RectificationMeasure rectificationMeasure = new RectificationMeasure()
                .setHiddenRiskId(dto.getHiddenRiskId())
                .setMeasureContent(dto.getMeasureContent())
                .setResponsiblePersonId(dto.getResponsiblePersonId())
                .setStartTime(dto.getStartTime())
                .setCompletionTime(dto.getCompletionTime())
                .setEffectDescription(dto.getEffectDescription());
        ThrowUtil.throwIf(
                rectificationMeasureMapper.insert(rectificationMeasure) != 1,
                () -> new SystemException(DatabaseExceptionCode.INSERT_ERROR));
        // 创建一个审批流程
        approvalFacadeService.createApprovalProcess(dto.getApprovalFlowCreateDto());
    }

    @Override
    public Page<RectificationMeasureDTO> getMyRectificationMeasurePageDTO(MyRectificationMeasurePageQueryDTO dto) {
        Date startTime = dto.getStartTime();
        Date completionTime = dto.getCompletionTime();
        // 根据业务号码(隐患id),查询对应的整改措施
        MyApprovalNodeRequest request = new MyApprovalNodeRequest().setApproverId(
                UserIdContextHolder.get());
        request.setCurrent(dto.getCurrent()).setPageSize(dto.getPageSize());
        RpcPageResult<String> rpcPageResult = approvalFacadeService.getMyApprovalNode(request);
        List<RectificationMeasure> list = rectificationMeasureMapper.selectList(
                Wrappers.<RectificationMeasure>lambdaQuery()
                        .in(RectificationMeasure::getHiddenRiskId, rpcPageResult.getRecords())
                        .eq(startTime != null, RectificationMeasure::getStartTime, startTime)
                        .eq(completionTime != null, RectificationMeasure::getCompletionTime, completionTime)
        );
        List<RectificationMeasureDTO> resultRecord = list
                .stream()
                .map(RectificationMeasure::toDTO)
                .toList();
        return PageUtil.convert2Page(rpcPageResult, resultRecord);
    }

    @Override
    public Page<RectificationMeasureVO> getMyRectificationMeasurePageVO(MyRectificationMeasurePageQueryDTO dto) {
        RectificationMeasureService rectificationMeasureService = (RectificationMeasureService) AopContext.currentProxy();
        Page<RectificationMeasureDTO> pageDto = rectificationMeasureService.getMyRectificationMeasurePageDTO(dto);
        List<RectificationMeasureVO> list = pageDto.getRecords().parallelStream().map(item -> {
            String responsiblePersonId = item.getResponsiblePersonId();
            String responsiblePersonName = userNameCache.get(responsiblePersonId);
            if (responsiblePersonName == null) {
                responsiblePersonName = userFacadeService.getRealNameById(responsiblePersonId);
            }
            String hiddenRiskName = hiddenRiskMapper.selectById(item.getHiddenRiskId()).getName();
            return item.toVO(hiddenRiskName, responsiblePersonName);
        }).toList();
        return PageUtil.convert2Page(pageDto, list);
    }
}




