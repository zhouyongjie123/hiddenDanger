package com.zyj.hiddendanger.risk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.dto.MyRectificationMeasurePageQueryDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureApprovalProcessVO;
import com.zyj.hiddendanger.model.service.risk.vo.RectificationMeasureVO;

public interface RectificationMeasureService extends IService<RectificationMeasure> {
    void report(RectificationMeasureReportDTO rectificationMeasureReportDTO);

    Page<RectificationMeasureDTO> getMyRectificationMeasurePageDTO(MyRectificationMeasurePageQueryDTO dto);

    Page<RectificationMeasureVO> getMyRectificationMeasurePageVO(MyRectificationMeasurePageQueryDTO dto);

    RectificationMeasureVO getRectificationMeasureByHiddenRiskId(String riskId);

    /**
     * 获取整改流程
     */
    RectificationMeasureApprovalProcessVO getRectificationMeasureApprovalProcessVOByHiddenRiskId(String riskId);
}
