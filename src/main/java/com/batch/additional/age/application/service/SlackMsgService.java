package com.batch.additional.age.application.service;

import com.batch.additional.age.application.exception.SlackSendMsgFailureException;
import com.batch.additional.age.domain.repository.NotifyRepository;
import com.batch.additional.age.domain.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackMsgService implements MsgService {

    private final NotifyRepository slackNotifyRepository;

    /**
     * Slackにメッセージを送信する
     *
     * @param msg : 送信メッセージ
     */
    @Override
    public void sendMessage(final String msg) {

        try {
            slackNotifyRepository.notify(msg);
        } catch (final Exception exception) {
            throw new SlackSendMsgFailureException("Slackに通知を送信する際に異常が発生しました。", exception);
        }
    }
}
