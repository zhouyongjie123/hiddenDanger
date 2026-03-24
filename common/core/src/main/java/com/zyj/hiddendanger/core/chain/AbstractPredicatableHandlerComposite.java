package com.zyj.hiddendanger.core.chain;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractPredicatableHandlerComposite<R, V> implements PredicatableHandler<R, V> {
    protected final List<PredicatableHandler<R, V>> predicatableHandlers;

    protected AbstractPredicatableHandlerComposite(List<PredicatableHandler<R, V>> predicatableHandlers) {
        this.predicatableHandlers = predicatableHandlers;
    }

    @SafeVarargs
    protected AbstractPredicatableHandlerComposite(PredicatableHandler<R, V>... predicatableHandlers) {
        this(Arrays.asList(predicatableHandlers));
    }

    @Override
    public Boolean isSupported(V value) {
        // 组合器是第一个节点, 所以直接返回true
        return Boolean.TRUE;
    }

    @Override
    public R handle(V value) {
        for (PredicatableHandler<R, V> predicatableHandler : predicatableHandlers) {
            if (predicatableHandler.isSupported(value)) {
                return predicatableHandler.handle(value);
            }
        }
        throw new UnsupportedChainOperationException("链中没有可处理的handler");
    }
}
