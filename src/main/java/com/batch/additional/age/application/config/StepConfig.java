package com.batch.additional.age.application.config;

import com.batch.additional.age.application.job.age.DbUserProcessor;
import com.batch.additional.age.application.job.age.DbUserReader;
import com.batch.additional.age.application.job.age.DbUserWriter;
import com.batch.additional.age.application.job.listener.AgeStepListener;
import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
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

    private static final String AGE_ADDITIONAL_STEP_NAME = "ageAdditionalStep";
    private static final int CHUNK_SIZE = 5;

    private final StepBuilderFactory stepBuilderFactory;
    private final AgeStepListener stepListener;

    // 年齢更新処理
    private final DbUserReader dbUserReader;
    private final DbUserProcessor dbUserProcessor;
    private final DbUserWriter dbUserWriter;


    /**
     * 年齢加算処理を行うStep
     *
     * @return Step
     */
    @Bean(name = AGE_ADDITIONAL_STEP_NAME)
    public Step ageAdditionalStep() {
        return stepBuilderFactory.get(AGE_ADDITIONAL_STEP_NAME)
                .listener(stepListener)
                .<DbUserInfo, DbUserInfo>chunk(CHUNK_SIZE)
                .reader(dbUserReader)
                .processor(dbUserProcessor)
                .writer(dbUserWriter)
                .build();
    }
}
