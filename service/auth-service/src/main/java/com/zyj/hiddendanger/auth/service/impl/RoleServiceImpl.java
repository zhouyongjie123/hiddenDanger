package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.RoleMapper;
import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.UnknownExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Role;
import com.zyj.hiddendanger.model.service.auth.dto.RoleAddDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<RoleSelectionVO> getSelectionVO() {
        return roleMapper.getSelectionVO();
    }

    @Override
    public RoleSelectionVO addRole(RoleAddDTO dto) {
        ThrowUtil.throwIfTrue(
                isRoleExist(dto.getRoleCode(), dto.getRoleName()),
                () -> new AuthException(AuthExceptionCode.ROLE_EXIST));
        Role role = new Role().setRoleName(dto.getRoleName()).setRoleCode(dto.getRoleCode());
        ThrowUtil.supplyWithExceptionTranslation(
                () -> roleMapper.insert(role), Exception.class,
                (e) -> new SystemException(UnknownExceptionCode.DATABASE_INSERT_ERROR));
        return role.toRoleSelectionVO();
    }

    @Override
    public void deleteById(String id) {
        ThrowUtil.throwIf(roleMapper.deleteById(id) != 1, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
    }

    @Override
    public List<RoleSelectionVO> getLeaderSelectVO() {
        return roleMapper.getLeaderSelectVO();
    }

    private boolean isRoleExist(String roleCode, String roleName) {
        return count(
                new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName).eq(Role::getRoleCode, roleCode)) > 0;
    }
}




