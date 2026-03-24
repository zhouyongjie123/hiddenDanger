package com.zyj.hiddendanger.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractExceptionCode implements ExceptionCode {
    private String code;

    private String message;
}
