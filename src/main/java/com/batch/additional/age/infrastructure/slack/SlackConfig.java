package com.batch.additional.age.infrastructure.slack;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * Slack設定定義クラス
 */
@Getter
@Component
@ConstructorBinding
@ConfigurationProperties("extension.slack.webhook")
public class SlackConfig {

    @Value("${extension.slack.webhook.uri}")
    private String uri;

    @Value("${extension.slack.webhook.connectionTimeout}")
    private int connectionTimeout;

    @Value("${extension.slack.webhook.readTimeout}")
    private int readTimeout;

    /**
     * Slack向けのWebClientを作成
     *
     * @return : WebClient
     */
    @Bean(name = "slackWebClient")
    public WebClient slackRestTemplate() {

        final HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, this.connectionTimeout)
                .doOnConnected(connection -> connection.addHandlerLast(
                        new ReadTimeoutHandler(this.readTimeout)
                ));

        return WebClient.builder()
                .baseUrl(this.uri)
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
