package com.zyj.hiddendanger.core.util;


import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface ThrowingSupplier<T> extends Supplier<T> {
    T getWithException() throws Exception;

    default T get() {
        return (T)this.get(RuntimeException::new);
    }

    default T get(BiFunction<String, Exception, RuntimeException> exceptionWrapper) {
        try {
            return (T)this.getWithException();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw (RuntimeException)exceptionWrapper.apply(ex.getMessage(), ex);
        }
    }

    default ThrowingSupplier<T> throwing(final BiFunction<String, Exception, RuntimeException> exceptionWrapper) {
        return new ThrowingSupplier<T>() {
            public T getWithException() throws Exception {
                return (T)ThrowingSupplier.this.getWithException();
            }

            public T get() {
                return (T)this.get(exceptionWrapper);
            }
        };
    }

    static <T> ThrowingSupplier<T> of(ThrowingSupplier<T> supplier) {
        return supplier;
    }

    static <T> ThrowingSupplier<T> of(ThrowingSupplier<T> supplier, BiFunction<String, Exception, RuntimeException> exceptionWrapper) {
        return supplier.throwing(exceptionWrapper);
    }
}
