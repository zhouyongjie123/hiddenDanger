package com.zyj.hiddendanger.risk.facade;

import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.UnImplementationExceptionCode;
import com.zyj.hiddendanger.rpc.api.risk.response.HiddenRiskDepartmentStatisticResponse;
import com.zyj.hiddendanger.rpc.api.risk.service.HiddenRiskStatisticFacadeService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DubboService
@Service
@RequiredArgsConstructor
public class HiddenRiskStatisticFacadeServiceImpl implements HiddenRiskStatisticFacadeService {
    @Override
    public Map<String, HiddenRiskDepartmentStatisticResponse> getHiddenRiskDepartmentStatistic(
            List<String> departmentIds) {
        throw new SystemException(UnImplementationExceptionCode.METHOD_UNIMPLEMENT);
    }
}
