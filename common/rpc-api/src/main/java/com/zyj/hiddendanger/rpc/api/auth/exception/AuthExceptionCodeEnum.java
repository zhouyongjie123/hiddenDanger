package com.zyj.hiddendanger.rpc.api.auth.exception;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthExceptionCodeEnum implements ExceptionCode {
    AccountError("10001", "账号错误"),
    PasswordError("10002", "密码错误"),
    NotLogin("10003", "未登录"),
    ;

    private final String code;

    private final String message;
}
