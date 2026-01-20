package com.zyj.hiddendanger.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseResult<T> {
    private String code;

    private String message;

    private T data;

    public static final String SUCCESSFUL_CODE = "200";

    public static ResponseResult<Object> ok(String message) {
        return new ResponseResult<Object>()
                .setCode(SUCCESSFUL_CODE)
                .setMessage(message);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<T>()
                .setCode(SUCCESSFUL_CODE)
                .setMessage("ok")
                .setData(data);
    }

    public static <T> ResponseResult<T> ok(T data, String message) {
        return new ResponseResult<T>()
                .setCode(SUCCESSFUL_CODE)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseResult<T> fail(String code, String message) {
        return new ResponseResult<T>()
                .setCode(code)
                .setMessage(message);
    }
}
