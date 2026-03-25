package com.zyj.hiddendanger.auth.service;

import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;

public interface SessionService {
    UserLoginVO login(LoginRequestDTO loginRequestDTO);

    void logout();
}
