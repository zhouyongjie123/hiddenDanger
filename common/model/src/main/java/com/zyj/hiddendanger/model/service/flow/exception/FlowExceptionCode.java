package com.zyj.hiddendanger.model.service.flow.exception;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlowExceptionCode implements ExceptionCode {
    ILLEGAL_GRAPH("FLOW_GRAPH_ILLEGAL", "图非法"),
    ;

    private final String code;

    private final String message;
}
