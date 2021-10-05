package com.batch.additional.age.infrastructure.slack.model;

import lombok.Data;

@Data
public class SlackNotifyRequest {

    // 送信メッセージ
    private String text;

}
