package com.zyj.hiddendanger.core.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 状态机定义
 * S: 状态枚举
 * E: 事件枚举
 */
public interface StateMachine<S, E> {
    // 状态转换前hook
    default void beforeTransition(S currentState, E event) {
    }

    // 状态转换后hook
    default void afterTransition(S currentState, E event, S targetState) {
    }

    // 状态转移方法
    S transition(S currentState, E event);

    // 可以直接更改源状态的状态转移方法
    S transition(S currentState, E event,Boolean isOverride);
}
