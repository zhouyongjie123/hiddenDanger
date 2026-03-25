package com.zyj.hiddendanger.rpc.api.auth.exception;

import com.zyj.hiddendanger.core.exception.biz.BizException;

public class DepartmentException extends BizException {
    public DepartmentException(DepartmentExceptionCode departmentExceptionCode) {
        super(departmentExceptionCode);
    }

    public DepartmentException(DepartmentExceptionCode departmentExceptionCode, String message) {
        super(departmentExceptionCode, message);
    }
}
