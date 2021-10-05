package com.batch.additional.age.application.exception;

public class SlackSendMsgFailureException extends RuntimeException {

    private static final long serialVersionUID = -155054239153224993L;

    public SlackSendMsgFailureException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
