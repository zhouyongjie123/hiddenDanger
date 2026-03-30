package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.domain.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;

import java.util.List;

public interface DepartmentService extends IService<Department> {
    Page<DepartmentInfoVO> page(Long current, Long pageSize);

    List<DepartmentSelectionVO> getSelectionVo();
}
