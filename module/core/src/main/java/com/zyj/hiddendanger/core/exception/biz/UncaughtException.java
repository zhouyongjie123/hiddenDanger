package com.zyj.hiddendanger.core.exception.biz;

import com.zyj.hiddendanger.core.exception.AbstractExceptionCode;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import lombok.Getter;

public class UncaughtException extends SystemException {
    public UncaughtException(Throwable cause) {
        super(new UncaughtExceptionCode(cause));
    }

    public UncaughtException(Throwable cause, String message) {
        super(new UncaughtExceptionCode(cause), message);
    }

    @Getter
    public static final class UncaughtExceptionCode extends AbstractExceptionCode {
        private static final String code = "UNCAUGHT_EXCEPTION";

        public static final String message = "未正确捕获异常: ";

        private final Throwable cause;

        UncaughtExceptionCode(Throwable cause) {
            super(code, message);
            this.cause = cause;
        }

        @Override
        public String getMessage() {
            return message + cause.toString();
        }
    }
}
