package com.zyj.hiddendanger.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserInfoDTO getUserInfoDTOByAccount(String account);

    UserInfoDTO getUserInfoDTOById(String id);

    List<UserInfoDTO> getUserInfoByRoleId(String roleId);
}




