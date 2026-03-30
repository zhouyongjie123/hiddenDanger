package com.zyj.hiddendanger.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.Role;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleSelectionVO> getSelectionVO();
}




