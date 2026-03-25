package com.zyj.hiddendanger.rpc.api.auth.exception;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentExceptionCode implements ExceptionCode {
    ID_NOT_EXIST("4004", "部门id不存在");

    private final String code;

    private final String message;
}
