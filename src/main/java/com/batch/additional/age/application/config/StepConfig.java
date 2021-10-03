package com.batch.additional.age.application.config;

import com.batch.additional.age.application.job.CleanLogFileTasklet;
import com.batch.additional.age.application.job.LineNotifyTasklet;
import com.batch.additional.age.application.job.age.DbAccountProcessor;
import com.batch.additional.age.application.job.age.DbAccountReader;
import com.batch.additional.age.application.job.age.DbAccountWriter;
import com.batch.additional.age.application.job.listener.AgeStepListener;
import com.batch.additional.age.infrastructure.db.dto.DbAccountInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Stepの実行定義クラス
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AgeStepListener stepListener;

    // 年齢更新処理
    private final DbAccountReader dbAccountReader;
    private final DbAccountProcessor dbAccountProcessor;
    private final DbAccountWriter dbAccountWriter;

    // line通知
    private final LineNotifyTasklet lineNotifyTasklet;

    // ログファイル削除
    private final CleanLogFileTasklet cleanLogFileTasklet;

    // const
    private static final int CHUNK_SIZE = 5;
    private static final String AGE_ADDITIONAL_STEP_NAME = "ageAdditionalStep";
    private static final String LINE_NOTIFY_STEP_NAME = "lineNotifyStep";
    private static final String CLEAN_LOG_FILE_STEP_NAME = "cleanLogFileStep";

    /**
     * 年齢加算処理を行うStep
     *
     * @return Step
     */
    @Bean(name = AGE_ADDITIONAL_STEP_NAME)
    public Step ageAdditionalStep() {
        return stepBuilderFactory.get(AGE_ADDITIONAL_STEP_NAME)
                .listener(stepListener)
                .<DbAccountInfo, DbAccountInfo>chunk(CHUNK_SIZE)
                .reader(dbAccountReader)
                .processor(dbAccountProcessor)
                .writer(dbAccountWriter)
                .build();
    }

    /**
     * 年齢加算結果のLine通知を行うStep
     *
     * @return Step
     */
    @Bean(name = LINE_NOTIFY_STEP_NAME)
    public Step lineNotifyStep() {
        return stepBuilderFactory.get(LINE_NOTIFY_STEP_NAME)
                .listener(stepListener)
                .tasklet(lineNotifyTasklet)
                .build();
    }

    /**
     * ログファイルの削除を行うStep
     *
     * @return Step
     */
    @Bean(name = CLEAN_LOG_FILE_STEP_NAME)
    public Step cleanLogFileStep() {
        return stepBuilderFactory.get(CLEAN_LOG_FILE_STEP_NAME)
                .listener(stepListener)
                .tasklet(cleanLogFileTasklet)
                .build();
    }
}
