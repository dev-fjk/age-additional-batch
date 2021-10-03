package com.batch.additional.age.domain.service;

/**
 * メッセージを送信するServiceIF
 */
public interface MsgService {

    /**
     * メッセージの送信を行う
     *
     * @param msg : 送信メッセージ
     */
    void sendMessage(final String msg);
}
