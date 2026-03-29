package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.domain.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentVO;

public interface DepartmentService extends IService<Department> {
    Page<DepartmentVO> page(Long current,Long pageSize);

}
