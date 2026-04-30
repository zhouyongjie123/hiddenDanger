package com.zyj.hiddendanger.model.service.risk;

import com.zyj.hiddendanger.core.exception.biz.BizException;
import com.zyj.hiddendanger.model.service.risk.vo.RiskExceptionCode;

public class RiskException extends BizException {
    public RiskException(RiskExceptionCode riskExceptionCode) {
        super(riskExceptionCode);
    }

    public RiskException(RiskExceptionCode riskExceptionCode, String message) {
        super(riskExceptionCode, message);
    }
}
