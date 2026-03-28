package com.zyj.hiddendanger.rpc.api.auth.service.mock;

import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.auth.service.DepartmentFacadeService;

import java.util.List;

@RpcMockService
public class DepartmentFacadeServiceMock implements DepartmentFacadeService {
    @Override
    public String getDepartmentNameById(String departmentId) {
        return "";
    }

    @Override
    public List<String> getDepartmentNameByIds(List<String> departmentIds) {
        return List.of();
    }
}
