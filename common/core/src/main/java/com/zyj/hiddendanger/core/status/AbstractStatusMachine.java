package com.zyj.hiddendanger.core.status;

import java.util.Map;

public abstract class AbstractStatusMachine<S, E> implements StatusMachine<S, E> {
    // key: 当前状态, value: 允许的事件->目标状态
    protected Map<S, Map<E,S>> transitions;

    public AbstractStatusMachine(Map<S, Map<E, S>> transitions) {
        this.transitions = transitions;
    }

    private S doTransition(S currentStatus, E event) {
        beforeTransition(currentStatus, event);
        S targetStatus = transition(currentStatus, event);
        afterTransition(currentStatus, event, targetStatus);
        return targetStatus;
    }

    // 可以直接更改源状态的状态转移方法
    @Override
    public S transition(S currentStatus, E event, Boolean isOverride) {
        S targetStatus;
        if (isOverride != null && isOverride) {
            // 原地覆盖源对象
            currentStatus = doTransition(currentStatus, event);
            targetStatus = currentStatus;
        } else {
            targetStatus = doTransition(currentStatus, event);
        }
        return targetStatus;
    }

    @Override
    public void beforeTransition(S currentStatus, E event) {
    }

    @Override
    public void afterTransition(S currentStatus, E event, S targetStatus) {
    }
}
