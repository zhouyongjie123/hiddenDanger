package com.zyj.hiddendanger.core.status;

/**
 * 状态机定义
 * S: 状态枚举
 * E: 事件枚举
 */
public interface StatusMachine<S, E> {
    // 状态转换前hook
    void beforeTransition(S currentStatus, E event);

    // 状态转换后hook
    void afterTransition(S currentStatus, E event, S targetStatus);

    // 状态转移方法
    S transition(S currentStatus, E event);

    // 可以直接更改源状态的状态转移方法
    S transition(S currentStatus, E event, Boolean isOverride);
}
