package com.zyj.hiddendanger.core.context;

public final class UserIdContextHolder {
    private final static ContextHolder<String> INSTANCE = new ContextHolder<>("UserId上下文获取失败");

    public static void set(String value) {
        INSTANCE.set(value);
    }

    public static String get() {
        return INSTANCE.get();
    }

    public static void remove() {
        INSTANCE.remove();
    }
}
