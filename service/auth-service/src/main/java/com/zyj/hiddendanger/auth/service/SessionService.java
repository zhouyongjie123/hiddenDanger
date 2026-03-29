package com.zyj.hiddendanger.auth.service;

import com.zyj.hiddendanger.model.service.auth.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.service.auth.vo.UserLoginVO;

public interface SessionService {
    UserLoginVO login(LoginRequestDTO loginRequestDTO);

    void logout();
}
