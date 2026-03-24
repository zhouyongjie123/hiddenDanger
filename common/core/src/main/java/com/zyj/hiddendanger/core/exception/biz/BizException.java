package com.zyj.hiddendanger.core.exception.biz;

import com.zyj.hiddendanger.core.exception.AbstractExceptionCode;
import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException implements Serializable {
    private ExceptionCode exceptionCode;

    public BizException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BizException(String code, String message) {
        this.exceptionCode = new AbstractExceptionCode(code, message) {
        };
    }

    public BizException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public BizException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public BizException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                        ExceptionCode exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }

    @Serial
    private static final long serialVersionUID = -5608773161727610665L;
}
