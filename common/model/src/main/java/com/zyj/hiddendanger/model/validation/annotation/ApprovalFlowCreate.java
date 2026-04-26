package com.zyj.hiddendanger.model.validation.annotation;

import com.zyj.hiddendanger.model.validation.ApprovalFlowCreateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ApprovalFlowCreateValidator.class) // 绑定校验器
public @interface ApprovalFlowCreate {
    String message() default "审批流程创建校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
