package com.batch.additional.age.infrastructure.slack;

import com.batch.additional.age.domain.repository.NotifyRepository;
import com.batch.additional.age.infrastructure.slack.model.SlackAttachment;
import com.batch.additional.age.infrastructure.slack.model.SlackNotifyRequest;
import java.util.ArrayList;
import java.util.List;
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

    private final WebClient slackWebClient;

    /**
     * Slackに通知を送信する
     *
     * @param msg : 送信メッセージ
     */
    @Override
    public void notify(final String msg) {
        log.info("Slack に通知を送信します。 送信メッセージ : {}", msg);

        final List<SlackAttachment> attachments = new ArrayList<>();
        attachments.add(new SlackAttachment(
                "2eb886", "年齢加算バッチ(AGE-ADDITIONAL-BATCH)", msg
        ));

        final SlackNotifyRequest request = new SlackNotifyRequest();
        request.setAttachments(attachments);

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
