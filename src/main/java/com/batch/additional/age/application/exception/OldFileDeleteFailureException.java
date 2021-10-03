package com.batch.additional.age.application.exception;

public class OldFileDeleteFailureException extends RuntimeException {

    private static final long serialVersionUID = -7333821513407449679L;

    public OldFileDeleteFailureException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
