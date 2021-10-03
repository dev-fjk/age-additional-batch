package com.batch.additional.age.application.exception;

public class LineSendMsgFailureException extends RuntimeException {

    private static final long serialVersionUID = 7811555950931907489L;

    public LineSendMsgFailureException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
