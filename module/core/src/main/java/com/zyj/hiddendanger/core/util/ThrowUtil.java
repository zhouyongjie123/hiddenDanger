package com.zyj.hiddendanger.core.util;

import com.zyj.hiddendanger.core.exception.biz.UncaughtException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ThrowUtil {
    private ThrowUtil() {

    }

    public static <T extends Throwable> void throwIf(Boolean condition, T throwable) throws T {
        Optional.ofNullable(condition) // 将 condition 包装为 Optional
                .filter(c -> !c)      // 如果 condition 为 false，保留
                .orElseThrow(() -> throwable); // 否则抛出异常
    }

    public static <T extends RuntimeException> void throwIf(Boolean condition, T runtimeException) {
        Optional
                .ofNullable(condition)
                .filter(c -> !c)
                .orElseThrow(() -> runtimeException);
    }

    public static <T extends RuntimeException> void throwIfNot(Boolean condition, T runtimeException) {
        throwIf(!condition, runtimeException);
    }

    public static <T extends RuntimeException> void throwIfNull(Object object, T runtimeException) {
        Optional
                .ofNullable(object)
                .orElseThrow(() -> runtimeException);
    }

    public static <T extends RuntimeException> void throwIfNotNull(Object object, T runtimeException) {
        Optional
                .ofNullable(object)
                .ifPresent(o -> {
                    throw runtimeException;
                });
    }

    public static <T extends RuntimeException> void throwIfBlank(String string, T runtimeException) {
        if (string == null || string.isEmpty()) {
            throw runtimeException;
        }
    }

    public static <T extends RuntimeException, E> E supplyWithExceptionTranslation(ThrowingSupplier<E> command,
                                                                                   List<Class<? extends Exception>> targetExceptions,
                                                                                   Function<Exception, T> exceptionSupplier) {
        try {
            return command.get();
        } catch (Exception e) {
            if (targetExceptions
                    .stream()
                    .anyMatch(clazz -> clazz.isInstance(e))) {
                throw exceptionSupplier.apply(e);
            }
            throw new UncaughtException(e);
        }
    }

    public static <T extends RuntimeException, E> E supplyWithExceptionTranslation(ThrowingSupplier<E> command,
                                                                                   Class<? extends Exception> targetException,
                                                                                   Function<Exception, T> exceptionSupplier) {
        return supplyWithExceptionTranslation(command, List.of(targetException), exceptionSupplier);
    }
}
