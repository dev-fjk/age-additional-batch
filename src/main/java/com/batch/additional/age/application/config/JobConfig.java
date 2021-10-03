package com.batch.additional.age.application.config;

import com.batch.additional.age.application.job.listener.AgeJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ジョブの実行定義クラス
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@ConditionalOnProperty(value = {"spring.batch.job.names"})
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final AgeJobListener jobListener;

    // Steps
    private final Step ageAdditionalStep;
    private final Step cleanLogFileStep;

    // consts
    private static final String AGE_ADDITIONAL_JOB_NAME = "ageAdditionalJob";
    private static final String CLEAN_LOG_FILE_JOB_NAME = "cleanLogFileJob";

    /**
     * DBのユーザーの年齢加算処理とLine通知を行うJob
     *
     * @return Job
     */
    @Bean(name = AGE_ADDITIONAL_JOB_NAME)
    public Job ageAdditionalJob() {
        return jobBuilderFactory.get(AGE_ADDITIONAL_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(ageAdditionalStep)
                .build();
    }

    /**
     * DBのユーザーの年齢加算処理とLine通知を行うJob
     *
     * @return Job
     */
    @Bean(name = CLEAN_LOG_FILE_JOB_NAME)
    public Job cleanLogFileJob() {
        return jobBuilderFactory.get(CLEAN_LOG_FILE_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(cleanLogFileStep)
                .build();
    }
}
