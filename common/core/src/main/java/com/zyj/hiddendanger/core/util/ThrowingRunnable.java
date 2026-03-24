package com.zyj.hiddendanger.core.util;

@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws Exception;
}
