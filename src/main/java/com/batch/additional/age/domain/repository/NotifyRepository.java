package com.batch.additional.age.domain.repository;

/**
 * 通知を行うリポジトリIF
 */
public interface NotifyRepository {

    /**
     * LineNotifyで通知を行う
     *
     * @param msg : 送信メッセージ
     */
    void notify(final String msg) throws Exception;
}
