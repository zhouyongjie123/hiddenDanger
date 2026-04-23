package com.zyj.hiddendanger.risk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.RectificationMeasure;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureDTO;
import com.zyj.hiddendanger.model.service.risk.dto.RectificationMeasureReportDTO;

import java.util.List;

public interface RectificationMeasureService extends IService<RectificationMeasure> {
    void report(RectificationMeasureReportDTO rectificationMeasureReportDTO);

    List<RectificationMeasureDTO> getMyRectificationMeasureList();
}
