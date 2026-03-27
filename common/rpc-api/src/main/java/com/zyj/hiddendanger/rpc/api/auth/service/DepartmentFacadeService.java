package com.zyj.hiddendanger.rpc.api.auth.service;

import java.util.List;

public interface DepartmentFacadeService {
    String getDepartmentNameById(String departmentId);

    List<String> getDepartmentNameByIds(List<String> departmentIds);
}
