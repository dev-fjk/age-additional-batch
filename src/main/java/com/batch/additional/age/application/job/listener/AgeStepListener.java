package com.batch.additional.age.application.job.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Stepの事前処理と事後処理実行クラス
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AgeStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        log.info("ステップ開始 : {} , ステータス : {}", stepExecution.getStepName(), stepExecution.getStatus());
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("ステップ終了 : {} , ステータス : {}", stepExecution.getStepName(), stepExecution.getStatus());
        log.info("ExecutionContext : {}", stepExecution.getExecutionContext());
        log.info("Exit Status : {}", stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
