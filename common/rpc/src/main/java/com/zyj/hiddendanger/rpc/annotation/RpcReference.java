package com.zyj.hiddendanger.rpc.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcReference {
    // 只保留 Dubbo 原生需要的属性，没有 mock！
    String version() default "1.0.0";

    String group() default "";

    int timeout() default 3000;
}

