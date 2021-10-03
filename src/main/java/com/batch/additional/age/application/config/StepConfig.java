package com.batch.additional.age.application.config;

import com.batch.additional.age.application.job.SampleTasklet;
import com.batch.additional.age.application.job.listener.AgeStepListener;
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

    private final StepBuilderFactory stepBuilderFactory;
    private final AgeStepListener stepListener;

    private final SampleTasklet sampleTasklet;

    /**
     * 年齢加算処理を行うStep
     *
     * @return Step
     */
    @Bean(name = AGE_ADDITIONAL_STEP_NAME)
    public Step ageAdditionalStep() {
        return stepBuilderFactory.get(AGE_ADDITIONAL_STEP_NAME)
                .listener(stepListener)
                .tasklet(sampleTasklet)
                .build();
    }
}
