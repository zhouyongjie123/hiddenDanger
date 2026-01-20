package com.zyj.hiddendanger.rpc.facade;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.remote.response.ResponseCode;
import com.zyj.hiddendanger.core.exception.biz.BizException;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.annotation.Resource;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class FacadeAspect {
    private static final Logger log = LoggerFactory.getLogger(FacadeAspect.class);

    @Resource
    private ConfigurableApplicationContext applicationContext;

    @Around("@within(com.zyj.hiddendanger.rpc.facade.Facade)")
    public Object handleFacade(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Object[] args = pjp.getArgs();
        String applicationName = applicationContext.getEnvironment()
                                                   .getProperty("spring.application.name");
        log.info("{} call RPC , method = {} , args = {}", applicationName, method.getName(), JSON.toJSONString(args));
        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getMethod()
                                                                    .getReturnType();
        //循环遍历所有参数，进行参数校验
        for (Object parameter : args) {
            try {
                BeanValidator.validateObject(parameter);
            } catch (ValidationException e) {
                printLog(stopWatch, method, args, "failed to validate", null, e);
                return getFailedResponse(returnType, e);
            }
        }
        try {
            // 目标方法执行
            Object response = pjp.proceed();
            enrichObject(response);
            printLog(stopWatch, method, args, applicationName + " call RPC end", response, null);
            return response;
        } catch (Throwable throwable) {
            // 如果执行异常，则返回一个失败的response
            printLog(stopWatch, method, args, "failed to execute", null, throwable);
            return getFailedResponse(returnType, throwable);
        }
    }


    private void printLog(StopWatch stopWatch, Method method, Object[] args, String action, Object response,
                          Throwable throwable) {
        try {
            // 因为此处有JSON.toJSONString，可能会有异常，需要进行捕获，避免影响主干流程
            log.info(getInfoMessage(action, stopWatch, method, args, response, throwable), throwable);
            // 如果校验失败，则返回一个失败的response
        } catch (Exception e1) {
            log.error("log failed", e1);
        }
    }

    private String getInfoMessage(String action, StopWatch stopWatch, Method method, Object[] args, Object response,
                                  Throwable exception) {

        StringBuilder stringBuilder = new StringBuilder(action);
        stringBuilder.append(" ,method = ");
        stringBuilder.append(method.getName());
        stringBuilder.append(" ,cost = ");
        stringBuilder.append(stopWatch.getTime())
                     .append(" ms");
        if (response instanceof BaseResponse) {
            stringBuilder.append(" ,success = ");
            stringBuilder.append(((BaseResponse) response).getSuccess());
        }
        if (exception != null) {
            stringBuilder.append(" ,success = ");
            stringBuilder.append(false);
        }
        stringBuilder.append(" ,args = ");
        stringBuilder.append(JSON.toJSONString(Arrays.toString(args)));

        if (response != null) {
            stringBuilder.append(" ,resp = ");
            stringBuilder.append(JSON.toJSONString(response));
        }

        if (exception != null) {
            stringBuilder.append(" ,exception = ");
            stringBuilder.append(exception.getMessage());
        }
        if (response instanceof BaseResponse baseResponse) {
            if (!baseResponse.getSuccess()) {
                stringBuilder.append(" , execute_failed");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 定义并返回一个通用的失败响应
     */
    private Object getFailedResponse(Class<?> returnType, Throwable throwable)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //如果返回值的类型为BaseResponse 的子类，则创建一个通用的失败响应
        Constructor<?> constructor = returnType.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        if (instance instanceof BaseResponse response) {
            response.setSuccess(false);
            if (throwable instanceof BizException bizException) {
                response.setResponseMessage(bizException.getExceptionCode()
                                                        .getMessage());
                response.setResponseCode(bizException.getExceptionCode()
                                                     .getCode());
            } else if (throwable instanceof SystemException systemException) {
                response.setResponseMessage(systemException.getExceptionCode()
                                                           .getMessage());
                response.setResponseCode(systemException.getExceptionCode()
                                                        .getCode());
            } else {
                response.setResponseMessage(throwable.toString());
                response.setResponseCode(ResponseCode.BIZ_ERROR.name());
            }

            return response;
        }
        log.error("failed to getFailedResponse , returnType ({}) is not instanceof BaseResponse", returnType);
        return null;
    }

    /**
     * 将response的信息补全，主要是code和message
     */
    private void enrichObject(Object response) {
        if (response instanceof BaseResponse baseResponse) {
            if (baseResponse.getSuccess()) {
                // 如果状态是成功的，需要将未设置的responseCode设置成SUCCESS
                if (StringUtils.isEmpty(baseResponse.getResponseCode())) {
                    baseResponse.setResponseCode(ResponseCode.SUCCESS.name());
                }
            } else {
                // 如果状态是成功的，需要将未设置的responseCode设置成BIZ_ERROR
                if (StringUtils.isEmpty(baseResponse.getResponseCode())) {
                    baseResponse.setResponseCode(ResponseCode.BIZ_ERROR.name());
                }
            }
        }
    }
}
