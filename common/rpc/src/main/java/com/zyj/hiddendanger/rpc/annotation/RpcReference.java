package com.zyj.hiddendanger.rpc.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcReference {
    String version() default "";

    String group() default "";

    int timeout() default 3000;
}

