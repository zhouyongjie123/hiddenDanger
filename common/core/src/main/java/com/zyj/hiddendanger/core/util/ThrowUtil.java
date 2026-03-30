package com.zyj.hiddendanger.core.util;

import com.zyj.hiddendanger.core.exception.biz.UncaughtException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThrowUtil {
    private ThrowUtil() {
    }

    public static <T extends RuntimeException> void throwIfTrue(
            Boolean condition, Supplier<T> exceptionSupplier) {
        Optional
                .ofNullable(condition)
                .filter(c -> !c)
                .orElseThrow(exceptionSupplier);
    }

    public static <T extends RuntimeException> void throwIf(
            Boolean condition, Supplier<T> exceptionSupplier) {
        throwIfTrue(condition, exceptionSupplier);
    }

    public static <T extends RuntimeException> void throwIfFalse(
            Boolean condition, Supplier<T> exceptionSupplier) {
        throwIfTrue(!condition, exceptionSupplier);
    }


    public static <T extends RuntimeException> void throwIfNull(
            Object object, Supplier<T> exceptionSupplier) {
        Optional
                .ofNullable(object)
                .orElseThrow(exceptionSupplier);
    }

    public static <T extends RuntimeException> void throwIfNotNull(
            Object object, Supplier<T> exceptionSupplier) {
        Optional
                .ofNullable(object)
                .ifPresent(o -> {
                    throw exceptionSupplier.get();
                });
    }

    public static <T extends RuntimeException> void throwIfBlank(
            String string, Supplier<T> exceptionSupplier) {
        if (string == null || string.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }

    // 运行一段代码,如果抛出特定的异常,则转换成指定的异常

    /**
     *
     * @param command 要运行的代码
     * @param targetExceptions 需要转换的异常类型
     * @param exceptionSupplier 异常转换函数
     * @return 未出现异常的情况下,command的返回值
     * @param <T> 异常类型
     * @param <E> 返回值类型
     */
    public static <T extends RuntimeException, E> E supplyWithExceptionTranslation(
            ThrowingSupplier<E> command,
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

    public static <T extends RuntimeException, E> E supplyWithExceptionTranslation(
            ThrowingSupplier<E> command,
            Class<? extends Exception> targetException,
            Function<Exception, T> exceptionSupplier) {
        return supplyWithExceptionTranslation(command, List.of(targetException), exceptionSupplier);
    }
}
