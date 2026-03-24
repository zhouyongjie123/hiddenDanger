package com.zyj.hiddendanger.core.chain;

import java.io.Serial;
import java.io.Serializable;

public class UnsupportedChainOperationException extends RuntimeException implements Serializable {

    public UnsupportedChainOperationException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
