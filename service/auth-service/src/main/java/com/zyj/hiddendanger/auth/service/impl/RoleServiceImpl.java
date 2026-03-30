package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.model.domain.Role;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<RoleSelectionVO> getSelectionVO() {
        return roleMapper.getSelectionVO();
    }
}




