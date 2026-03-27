package com.zyj.hiddendanger.model.service.auth.exception;

import com.zyj.hiddendanger.core.exception.biz.BizException;

public class DepartmentException extends BizException {
    public DepartmentException(DepartmentExceptionCode departmentExceptionCode) {
        super(departmentExceptionCode);
    }

    public DepartmentException(DepartmentExceptionCode departmentExceptionCode, String message) {
        super(departmentExceptionCode, message);
    }
}
