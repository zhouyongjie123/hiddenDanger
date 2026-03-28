package com.zyj.hiddendanger.rpc.api.auth.service.mock;

import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;

import java.util.List;

@RpcMockService
public class DepartmentFacadeServiceMock implements DepartmentFacadeService {
    @Override
    public String getDepartmentNameById(String departmentId) {
        return "mockDepartment_" + departmentId;
    }

    @Override
    public List<String> getDepartmentNameByIds(List<String> departmentIds) {
        return departmentIds.stream().map(id -> "mockDepartment_" + id).toList();
    }
}
