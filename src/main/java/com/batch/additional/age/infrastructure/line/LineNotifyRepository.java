package com.batch.additional.age.infrastructure.line;

import com.batch.additional.age.domain.repository.NotifyRepository;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * ラインに通知メッセージを送信するリポジトリ
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LineNotifyRepository implements NotifyRepository {

    private final LineConfig lineConfig;

    /**
     * LineNotifyを使用してLineのグループチャットにメッセージを送信する
     *
     * @param msg : 送信メッセージ
     * @throws Exception : メッセージ送信時例外
     */
    @Override
    public void notify(final String msg) throws Exception {
        log.info("ラインに通知を送信します。 メッセージ : {}", msg);

        HttpURLConnection connection = null;

        try {

            final URL url = new URL(lineConfig.getUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Authorization", "Bearer " + lineConfig.getToken());

            // タイムアウト設定
            connection.setConnectTimeout(lineConfig.getConnectionTimeout());
            connection.setReadTimeout(lineConfig.getReadTimeout());

            final OutputStream os = connection.getOutputStream();
            final PrintWriter writer = new PrintWriter(os);
            writer.append("message=").append(URLEncoder.encode(msg, StandardCharsets.UTF_8)).flush();

            final InputStream is = connection.getInputStream();
            final BufferedReader r = new BufferedReader(new InputStreamReader(is));
            final String res = r.lines().collect(Collectors.joining());

            log.info("ラインに通知を送信しました。");
            log.info("response : {}", res);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
