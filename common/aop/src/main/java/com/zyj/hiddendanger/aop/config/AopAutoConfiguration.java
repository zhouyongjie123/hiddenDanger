package com.zyj.hiddendanger.aop.config;

import com.zyj.hiddendanger.aop.util.SpELValidatorAndParser;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@EnableAspectJAutoProxy(exposeProxy = true,
                        proxyTargetClass = true)
@Import(SpELValidatorAndParser.class)
public class AopAutoConfiguration {
}