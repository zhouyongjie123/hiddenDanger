package com.zyj.hiddendanger.core.chain;

public interface PredicatableHandler<R, V> {
    R handle(V value);

    Boolean isSupported(V value);
}
