package com.batch.additional.age.infrastructure.line;

import java.net.HttpURLConnection;
import java.net.URL;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ラインの設定定義クラスs
 */
@Getter
@Component
@ConstructorBinding
@ConfigurationProperties("extension.line")
public class LineConfig {

    // LineNotify url
    @Value("${extension.line.url}")
    private String url;

    // LineNotify Token
    @Value("${extension.line.token}")
    private String token;
}
