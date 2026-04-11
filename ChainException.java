package org.blockchain.exception;

public class ChainException extends RuntimeException {
    public ChainException(String message) {
        super(message);
    }

    public ChainException(String message, Throwable cause) {
        super(message, cause);
    }
}
