package com.batch.additional.age.infrastructure.slack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlackAttachment {

    private String color;
    private String title;
    private String text;
}
