package com.zyj.hiddendanger.web.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.zyj.hiddendanger.core.exception.biz.BizException;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
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

    @ExceptionHandler(SystemException.class)
    public ResponseResult<Object> exceptionHandler(SystemException systemException) {
        return new ResponseResult<>()
                .setCode(systemException.getExceptionCode()
                                        .getCode())
                .setMessage(systemException.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseResult<Object> handleNotLoginException(NotLoginException e) {
        return ResponseResult.fail("401", "登录已过期,请重新登录");
    }
    // 处理对象校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult()
                                                 .getFieldError())
                                .getDefaultMessage();
        return ResponseResult.fail("400", message);
    }
    // 处理 400 通用参数错误
    @ExceptionHandler(BindException.class)
    public ResponseResult<Map<String, String>> handleBindException(BindException e) {
        FieldError fieldError = e.getFieldError();
        String message = fieldError.getDefaultMessage();
        String field = fieldError.getField();
        Map<String, String> map = new HashMap<>();
        map.put(field, message);
        return ResponseResult.fail (map.toString(),"参数错误");
    }
}
