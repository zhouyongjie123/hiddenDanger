package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;

public interface UserService extends IService<User> {
    UserInfoDTO getUserInfoByAccount(String account);

    UserInfoVO register(UserRegisterDTO userRegisterDTO);

    Boolean isAccountExist(String account);
}
