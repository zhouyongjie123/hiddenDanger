package com.zyj.hiddendanger.rpc.api.risk.service;

import com.zyj.hiddendanger.rpc.api.risk.response.HiddenRiskDepartmentStatisticResponse;

import java.util.List;
import java.util.Map;

public interface HiddenRiskStatisticFacadeService {
    Map<String, HiddenRiskDepartmentStatisticResponse> getHiddenRiskDepartmentStatistic(List<String> departmentIds);
}
