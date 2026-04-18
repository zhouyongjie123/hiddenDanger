package com.zyj.hiddendanger.model.service.flow.exception;

import com.zyj.hiddendanger.core.exception.biz.BizException;

public class FlowException extends BizException {
    public FlowException(FlowExceptionCode flowExceptionCode) {
        super(flowExceptionCode);
    }
}
