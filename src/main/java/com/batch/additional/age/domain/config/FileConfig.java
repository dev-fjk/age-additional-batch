package com.batch.additional.age.domain.config;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ファイル DI定義クラス
 */
@Component
public class FileConfig {

    private static final String LOG_FILE_DIR = "log/";

    /**
     * ログファイルディレクトリのFileクラスのDI
     *
     * @return File
     */
    @Bean(name = "logFilePath")
    public File logFilePath() {
        return new File(LOG_FILE_DIR);
    }
}
