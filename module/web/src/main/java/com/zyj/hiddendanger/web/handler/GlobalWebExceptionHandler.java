package com.zyj.hiddendanger.web.handler;

import com.zyj.hiddendanger.core.exception.biz.BizException;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalWebExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ResponseResult<Object> exceptionHandler(BizException bizException) {
        return new ResponseResult<>()
                .setCode(bizException.getExceptionCode()
                                     .getCode())
                .setMessage(bizException.getMessage());
    }

    // 处理对象校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult()
                                                 .getFieldError())
                                .getDefaultMessage();
        return ResponseResult.fail("400", message);
    }
}
