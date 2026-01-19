package com.zyj.hiddendanger.core.exception.constant;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContextExceptionCodeEnum implements ExceptionCode {
    CONTEXT_GET_EXCEPTION("CONTEXT_GET_EXCEPTION", "上下文获取失败");

    private final String code;

    private final String message;
}
