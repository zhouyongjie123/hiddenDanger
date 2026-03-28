package com.zyj.hiddendanger.core.exception.sys;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SystemException extends RuntimeException {
    private ExceptionCode exceptionCode;

    protected SystemException(String code, String message) {
        this.exceptionCode = new ExceptionCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    protected SystemException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    protected SystemException(String message) {
        super(message);
    }
}
