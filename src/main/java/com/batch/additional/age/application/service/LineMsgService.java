package com.batch.additional.age.application.service;

import com.batch.additional.age.application.exception.LineSendMsgFailureException;
import com.batch.additional.age.domain.repository.NotifyRepository;
import com.batch.additional.age.domain.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Lineにメッセージを送信するサービス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LineMsgService implements MsgService {

    private final NotifyRepository lineNotifyRepository;

    /**
     * Lineへメッセージを送信する
     *
     * @param msg : 送信メッセージs
     */
    @Override
    public void sendMessage(final String msg) {

        try {
            // lineにメッセージを送信する
            lineNotifyRepository.notify(msg);
        } catch (final Exception exception) {
            throw new LineSendMsgFailureException("ラインの通知処理を行う際に異常が発生しました。", exception);
        }
    }
}
