package com.batch.additional.age.application.job;

import com.batch.additional.age.application.exception.OldFileDeleteFailureException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * ログファイルの削除を行う
 */
@Slf4j
@StepScope
@Component
@RequiredArgsConstructor
public class CleanLogFileTasklet implements Tasklet {

    private final File logFilePath;

    // ログファイル保持日数
    private static final int KEEPS_DAY_LOG_FILE = 1;

    /**
     * 古いログファイルの削除を行う
     * デフォルトは昨日のログファイルまで保持(一昨日以前のログファイルは削除)
     *
     * @param contribution : コントリビューション
     * @param chunkContext : chunk情報
     * @return repeatStatus
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) {
        log.info("cleanLogFile Tasklet Execute START");

        File[] logFiles = logFilePath.listFiles();
        log.info("ログファイル一覧 : {}", ArrayUtils.toString(logFiles));

        if (ArrayUtils.isEmpty(logFiles)) {
            log.info("ログファイルが存在しません。　処理を終了します。");
            return RepeatStatus.FINISHED;
        }

        // 削除境界日の取得
        LocalDate targetDate = LocalDate.now().minusDays(KEEPS_DAY_LOG_FILE);
        log.info("削除境界日 : {}", targetDate);

        try {
            // 警告対応でのrequireNonNull
            for (File tmp : Objects.requireNonNull(logFiles)) {
                final String fileName = tmp.getName();

                // _と.でログファイル名を分割
                final String[] splitFileName = fileName.split("[_.]", 0);
                log.info("分割したファイル日時 : {}", ArrayUtils.toString(splitFileName));

                final LocalDate checkDate = LocalDate.parse(splitFileName[1], DateTimeFormatter.ofPattern("yyyyMMdd"));
                log.info("ファイル削除境界日 : {},対象ファイルの日付 : {}", targetDate, checkDate);

                if (checkDate.isBefore(targetDate)) {
                    log.info("ファイルを削除します。 パス : {}", tmp.getPath());
                    Files.deleteIfExists(Paths.get(tmp.getPath()));
                } else {
                    log.info("削除対象ではありません。");
                }
            }

        } catch (final Exception exception) {
            throw new OldFileDeleteFailureException("ファイルの削除に失敗しました", exception);
        }

        log.info("cleanLogFile Tasklet Execute END");
        return RepeatStatus.FINISHED;
    }
}
