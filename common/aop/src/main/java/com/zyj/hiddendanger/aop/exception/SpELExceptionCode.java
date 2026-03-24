package com.zyj.hiddendanger.aop.exception;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpELExceptionCode implements ExceptionCode {
    SPEL_PARSE_EXCEPTION("SPEL_PARSE_EXCEPTION", "spEL表达式解析异常");

    private final String code;

    private final String message;
}
