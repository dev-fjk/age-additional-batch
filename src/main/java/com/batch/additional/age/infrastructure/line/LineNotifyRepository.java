package com.batch.additional.age.infrastructure.line;

import com.batch.additional.age.domain.repository.NotifyRepository;
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

    @Override
    public void notify(String msg) throws Exception {
        // TODO Lineに通知を送る処理の盛り込み
        log.info("トークン : {}", lineConfig.getToken());
        log.info("URL : {}", lineConfig.getUrl());
    }
}
