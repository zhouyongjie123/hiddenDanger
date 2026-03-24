package com.zyj.hiddendanger.core.exception;


import com.zyj.hiddendanger.core.exception.constant.ContextExceptionCodeEnum;
import com.zyj.hiddendanger.core.exception.sys.SystemException;

public class ContextException extends SystemException {
    public ContextException(ContextExceptionCodeEnum exceptionCode, String message) {
        super(exceptionCode.getCode(), message);
    }
}
