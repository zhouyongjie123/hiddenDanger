package com.zyj.hiddendanger.core.status;

/**
 * 状态机定义
 * S: 状态枚举
 * E: 事件枚举
 */
public interface StatusMachine<S, E> {
    // 状态转移方法
    S transition(S currentStatus, E event);
}
