package com.zyj.hiddendanger.core.exception.sys.code;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnknownExceptionCode implements ExceptionCode {
    DATABASE_INSERT_ERROR("10001", "数据库插入异常"),
    ;

    private final String code;

    private final String message;
}
