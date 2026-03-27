package com.zyj.hiddendanger.risk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.risk.service.DepartmentService;
import com.zyj.hiddendanger.risk.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




