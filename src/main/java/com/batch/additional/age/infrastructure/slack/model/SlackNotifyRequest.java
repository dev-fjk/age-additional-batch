package com.batch.additional.age.infrastructure.slack.model;

import java.util.List;
import lombok.Data;

@Data
public class SlackNotifyRequest {

    // 送信メッセージ
    private String text;
    private List<SlackAttachment> attachments;
}
