package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.Role;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<RoleSelectionVO> getSelectionVO();

    RoleSelectionVO addRole(String roleName);

    void deleteById(String id);
}
