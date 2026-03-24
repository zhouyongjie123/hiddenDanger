package com.zyj.hiddendanger.core.id;

@FunctionalInterface
public interface IdGenerator<T> {
    T generate();
}
