package com.zyj.hiddendanger.core.exception.sys;

import com.zyj.hiddendanger.core.exception.ExceptionCode;

public class RPCInjectionException extends SystemException {
    public RPCInjectionException(String message) {
        super(message);
    }

    public RPCInjectionException() {
        super();
    }
}