package com.zyj.hiddendanger.auth.facade;

import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.rpc.api.auth.exception.DepartmentException;
import com.zyj.hiddendanger.rpc.api.auth.exception.DepartmentExceptionCode;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Facade
@DubboService
@Service
@RequiredArgsConstructor
public class DepartmentFacadeServiceImpl implements DepartmentFacadeService {
    private final DepartmentService departmentService;

    @Override
    public String getDepartmentNameById(String departmentId) {
        Department department = departmentService.getById(departmentId);
        ThrowUtil.throwIfNull(department, () -> new DepartmentException(DepartmentExceptionCode.ID_NOT_EXIST));
        return department.getDepartmentName();
    }
}
