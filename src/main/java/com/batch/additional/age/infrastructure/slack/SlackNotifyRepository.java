package com.batch.additional.age.infrastructure.slack;

import com.batch.additional.age.domain.repository.NotifyRepository;
import com.batch.additional.age.infrastructure.slack.model.SlackNotifyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ラインに通知メッセージを送信するリポジトリ
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class SlackNotifyRepository implements NotifyRepository {

    private final SlackConfig slackConfig;
    private final WebClient slackWebClient;

    /**
     * Slackに通知を送信する
     *
     * @param msg : 送信メッセージ
     * @throws Exception : Slack通信時例外
     */
    @Override
    public void notify(final String msg) throws Exception {
        log.info("Slack に通知を送信します。 送信メッセージ : {}", msg);

        final SlackNotifyRequest request = new SlackNotifyRequest();
        request.setText(msg);

        // SLackに通知を送信
        final String slackResponse = slackWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), SlackNotifyRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Slackに通知を送信しました。 : {}", slackResponse);
    }
}
