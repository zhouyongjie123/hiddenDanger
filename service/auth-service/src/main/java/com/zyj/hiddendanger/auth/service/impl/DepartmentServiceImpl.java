package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




