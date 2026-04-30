package com.zyj.hiddendanger.model.service.risk.vo;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiskExceptionCode implements ExceptionCode {
    UNRECTIFIED_RISK("10001", "该隐患没有整改");

    private final String code;

    private final String message;
}
