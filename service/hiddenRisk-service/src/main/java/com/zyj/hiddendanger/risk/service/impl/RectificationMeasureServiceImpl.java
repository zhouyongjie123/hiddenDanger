package com.zyj.hiddendanger.risk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.DatabaseExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;
import com.zyj.hiddendanger.risk.mapper.RectificationMeasureMapper;
import com.zyj.hiddendanger.risk.service.RectificationMeasureService;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RectificationMeasureServiceImpl extends ServiceImpl<RectificationMeasureMapper, RectificationMeasure>
        implements RectificationMeasureService {
    private final RectificationMeasureMapper rectificationMeasureMapper;

    @RpcReference
    private ApprovalFacadeService approvalFacadeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(RectificationMeasureReportDTO dto) {
        RectificationMeasure rectificationMeasure = new RectificationMeasure().setHiddenRiskId(dto.getHiddenRiskId())
                                                                              .setMeasureContent(
                                                                                      dto.getMeasureContent())
                                                                              .setResponsiblePersonId(
                                                                                      dto.getResponsiblePersonId())
                                                                              .setStartTime(dto.getStartTime())
                                                                              .setCompletionTime(
                                                                                      dto.getCompletionTime())
                                                                              .setEffectDescription(
                                                                                      dto.getEffectDescription());
        ThrowUtil.throwIf(
                rectificationMeasureMapper.insert(rectificationMeasure) != 1, () -> new SystemException(
                        DatabaseExceptionCode.INSERT_ERROR));
        // 创建一个审批流程
        approvalFacadeService.createApprovalProcess(dto.getApprovalFlowCreateDTO());
    }

    @Override
    public List<RectificationMeasureDTO> getMyRectificationMeasureList() {
        return List.of();
    }
}




