package com.zyj.hiddendanger.model.exception.auth;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthExceptionCodeEnum implements ExceptionCode {
    AccountError("10001", "账号错误"),
    PasswordError("10002", "密码错误"),
    ;

    private final String code;

    private final String message;
}
