package com.zyj.hiddendanger.web.infrustructure.idempotent;

import com.zyj.hiddendanger.aop.util.SpELValidatorAndParser;
import com.zyj.hiddendanger.cache.Separator;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class IdempotentAspect {
    @Resource
    private RedissonClient redissonClient;

    @Around("@annotation(com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Idempotent anno = method.getAnnotation(Idempotent.class);
        // 1. 解析 SpEL key
        SpELValidatorAndParser spELValidatorAndParser = new SpELValidatorAndParser();
        Expression expression = spELValidatorAndParser.validateAndParse(anno.idempotentKey());
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        String key = expression.getValue(context, String.class);
        String redisKey = "idempotent" + Separator.DEFAULT_SEPARATOR + key;

        // 2. 尝试占锁
        RLock lock = redissonClient.getLock(redisKey);
        boolean success = lock.tryLock(anno.expireSeconds(), anno.unit());
        // 3. 占锁失败 → 重复执行 → 直接返回
        if (!success) {
            throw new RuntimeException(anno.message());
        }
        // 4. 占锁成功 → 执行业务
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            // 5. 业务异常 → 删除锁（允许重试）
            lock.forceUnlock();
            throw e;
        }
    }
}
