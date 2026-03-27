package com.zyj.hiddendanger.model.service.auth.exception;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthExceptionCode implements ExceptionCode {
    ACCOUNT_ERROR("10001", "账号错误"),
    PASSWORD_ERROR("10002", "密码错误"),
    NOT_LOGIN("10003", "未登录"),
    ID_NOT_EXIST("10004", "用户id不存在");

    private final String code;

    private final String message;
}
