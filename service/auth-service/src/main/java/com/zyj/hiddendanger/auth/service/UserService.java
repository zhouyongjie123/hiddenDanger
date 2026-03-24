package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.vo.UserLoginVO;

public interface UserService extends IService<User> {
    UserLoginVO getUserLoginVO(String account, String password);
}
