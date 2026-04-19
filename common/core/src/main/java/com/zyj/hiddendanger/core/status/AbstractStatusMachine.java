package com.zyj.hiddendanger.core.status;

import java.util.Map;

public abstract class AbstractStatusMachine<S, E> implements StatusMachine<S, E> {
    // key: 当前状态, value: 允许的事件->目标状态
    protected Map<S, Map<E, S>> transitions;

    public AbstractStatusMachine(Map<S, Map<E, S>> transitions) {
        this.transitions = transitions;
    }

    @Override
    public S transition(S currentStatus, E event) {
        beforeTransition(currentStatus, event);
        S targetStatus = doTransition(currentStatus, event);
        afterTransition(currentStatus, event, targetStatus);
        return targetStatus;
    }

    protected abstract S doTransition(S currentStatus, E event);


    // 状态转换前hook
    public void beforeTransition(S currentStatus, E event) {
    }

    // 状态转换后hook
    public void afterTransition(S currentStatus, E event, S targetStatus) {
    }
}
