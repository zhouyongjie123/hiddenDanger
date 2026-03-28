package com.zyj.hiddendanger.auth.facade;

import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.service.auth.exception.DepartmentException;
import com.zyj.hiddendanger.model.service.auth.exception.DepartmentExceptionCode;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<String> getDepartmentNameByIds(List<String> departmentIds) {
        List<Department> departments = departmentService.listByIds(departmentIds);
        return departments.stream().map(Department::getDepartmentName).toList();
    }
}
