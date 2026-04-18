package com.zyj.hiddendanger.web.infrustructure.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 幂等 key，支持 SpEL 表达式
     * 例如：#msg.flowProcessId
     */
    String idempotentKey();

    /**
     * 锁过期时间（防止死锁）
     */
    long expireSeconds() default 600;

    /**
     * 锁过期时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 提示信息
     */
    String message() default "幂等跳过执行";
}
