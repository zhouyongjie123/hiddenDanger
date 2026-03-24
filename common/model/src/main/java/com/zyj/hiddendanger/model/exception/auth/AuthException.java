package com.zyj.hiddendanger.model.exception.auth;

import java.io.Serial;
import java.io.Serializable;

public class AuthException extends RuntimeException implements Serializable {
    public AuthException(AuthExceptionCodeEnum authExceptionCode) {
        super(authExceptionCode.getCode());
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
