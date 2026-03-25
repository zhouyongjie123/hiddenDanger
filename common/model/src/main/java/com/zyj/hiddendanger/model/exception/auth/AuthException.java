package com.zyj.hiddendanger.model.exception.auth;

import com.zyj.hiddendanger.core.exception.biz.BizException;

public class AuthException extends BizException {
    public AuthException(AuthExceptionCodeEnum authExceptionCodeEnum) {
        super(authExceptionCodeEnum);
    }

    public AuthException(AuthExceptionCodeEnum authExceptionCodeEnum, String message) {
        super(authExceptionCodeEnum, message);
    }
}
