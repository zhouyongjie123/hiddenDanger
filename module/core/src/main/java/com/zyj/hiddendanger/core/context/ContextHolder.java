package com.zyj.hiddendanger.core.context;

import com.zyj.hiddendanger.core.util.ThrowUtil;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.zyj.jmartx.core.exception.constant.ContextExceptionCodeEnum.CONTEXT_GET_EXCEPTION;

@Setter
@Accessors(chain = true)
public class ContextHolder<T> {
    private ContextHolder() {
    }

    private static final Map<Class<?>, ThreadLocal<?>> threadLocalMap = new ConcurrentHashMap<>();

    private String exceptionMessage;

    @SuppressWarnings("unchecked")
    private ThreadLocal<T> getThreadLocal() {
        return (ThreadLocal<T>) threadLocalMap.computeIfAbsent(this.getClass(), k -> new ThreadLocal<>());
    }

    public void set(T value) {
        getThreadLocal().set(value);
    }

    public void remove(){
        getThreadLocal().remove();
    }

    public T get() {
        return ThrowUtil.supplyWithExceptionTranslation(getThreadLocal()::get, NullPointerException.class,
                                                        e -> new ContextException(CONTEXT_GET_EXCEPTION,
                                                                                  exceptionMessage));
    }

    public static <T> ContextHolder<T> create(Class<T> type, String message) {
        return new ContextHolder<T>().setExceptionMessage(message);
    }
}