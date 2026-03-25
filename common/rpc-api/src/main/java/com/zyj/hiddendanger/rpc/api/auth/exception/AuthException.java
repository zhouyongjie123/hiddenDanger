package com.zyj.hiddendanger.rpc.api.auth.exception;

import com.zyj.hiddendanger.core.exception.biz.BizException;

public class AuthException extends BizException {
    public AuthException(AuthExceptionCode authExceptionCode) {
        super(authExceptionCode);
    }

    public AuthException(AuthExceptionCode authExceptionCode, String message) {
        super(authExceptionCode, message);
    }
}
