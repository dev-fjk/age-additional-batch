package com.batch.additional.age.config;

import java.time.Clock;
import java.time.ZoneId;
import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * バッチの
 */
@Profile("!test")
@Configuration
public class BatchConfig extends DefaultBatchConfigurer {

    // 日本向けTimeZoneの設定
    private static final String JP_TIME_ZONE = "Asia/Tokyo";

    /**
     * データソースセット処理を空処理で上書きすることで Batchのメタデータ保持に MapRepositoryが使われるようになる
     *
     * @param dataSource : データソース
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        // 空処理
    }

    /**
     * 日本標準時を持つClockのDI生成
     *
     * @return Clock
     */
    @Bean(name = "clock")
    public Clock clock() {
        return Clock.system(ZoneId.of(JP_TIME_ZONE));
    }

}
