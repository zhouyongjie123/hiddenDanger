package com.zyj.hiddendanger.core.exception.sys.code;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnImplementationExceptionCode implements ExceptionCode {
    METHOD_UNIMPLEMENT("METHOD_UNIMPLEMENT", "方法未实现"),
    CLASS_UNIMPLEMENT("CLASS_UNIMPLEMENT", "类未实现"),
    ;
    private final String code;

    private final String message;
}
