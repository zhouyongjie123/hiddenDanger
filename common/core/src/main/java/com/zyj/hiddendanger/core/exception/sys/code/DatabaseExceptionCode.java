package com.zyj.hiddendanger.core.exception.sys.code;

import com.zyj.hiddendanger.core.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseExceptionCode implements ExceptionCode {
    INSERT_ERROR("10001", "数据库插入异常"),
    UPDATE_ERROR("10002", "数据库更新异常"),
    DELETE_ERROR("10003", "数据库删除异常"),
    SELECT_ERROR("10004", "数据库查询异常"),
    ID_NOT_FOUND("10005", "数据库查询结果为空");

    private final String code;

    private final String message;
}
