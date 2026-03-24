package com.zyj.hiddendanger.aop.exception;


import com.zyj.hiddendanger.core.exception.sys.SystemException;

public class SpELException extends SystemException {
    public SpELException(SpELExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
