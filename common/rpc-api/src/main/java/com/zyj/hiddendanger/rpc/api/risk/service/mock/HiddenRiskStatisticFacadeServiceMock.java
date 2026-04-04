package com.zyj.hiddendanger.rpc.api.risk.service.mock;

import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.risk.response.HiddenRiskDepartmentStatisticResponse;
import com.zyj.hiddendanger.rpc.api.risk.service.HiddenRiskStatisticFacadeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RpcMockService
public class HiddenRiskStatisticFacadeServiceMock implements HiddenRiskStatisticFacadeService {
    @Override
    public Map<String, HiddenRiskDepartmentStatisticResponse> getHiddenRiskDepartmentStatistic(
            List<String> departmentIds) {
        Map<String, HiddenRiskDepartmentStatisticResponse> result = new HashMap<>();
        for (int i = 0; i < departmentIds.size(); i++) {
            result.put(
                    departmentIds.get(i),
                    new HiddenRiskDepartmentStatisticResponse().setDepartmentId(departmentIds.get(i))
                                                               .setDepartmentLeaderId(
                                                                       "mock_user_id_" + i)
                                                               .setDepartmentName(
                                                                       "mock_dept_name_" + i)
                                                               .setTotalHiddenRiskCount(104L)
                                                               .setWaitRectifyHiddenRiskCount(23L)
                                                               .setRectifyingHiddenRiskCount(10L)
                                                               .setWaitAcceptanceHiddenRiskCount(
                                                                       12L)
                                                               .setClosedHiddenRiskCount(30L)
                                                               .setCanceledHiddenRiskCount(29L)
            );
        }
        return result;
    }
}
