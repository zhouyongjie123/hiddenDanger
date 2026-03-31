package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Role;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
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

    @Override
    public RoleSelectionVO addRole(String roleName) {
        ThrowUtil.throwIfTrue(this.isRoleExist(roleName), () -> new AuthException(AuthExceptionCode.ROLE_DUPLICATE));
        Role role = new Role().setRoleName(roleName);
        roleMapper.insert(role);
        return new RoleSelectionVO().setId(role.getId())
                                    .setRoleName(role.getRoleName());
    }

    @Override
    public void deleteById(String id) {
        ThrowUtil.throwIf(roleMapper.deleteById(id) != 1, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
    }

    @Override
    public Boolean isRoleExist(String roleName) {
        return this.exists(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName));
    }
}




