package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.UserRegisterDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserLoginVO;

public interface UserService extends IService<User> {
    UserLoginVO getUserLoginVO(String account, String password);

    UserInfoVO register(UserRegisterDTO userRegisterDTO);

    Boolean isAccountExist(String account);
}
