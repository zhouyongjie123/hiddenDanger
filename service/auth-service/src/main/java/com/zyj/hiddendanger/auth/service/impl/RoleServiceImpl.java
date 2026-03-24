package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.model.po.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




