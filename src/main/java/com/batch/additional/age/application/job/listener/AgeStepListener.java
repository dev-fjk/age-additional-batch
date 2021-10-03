package com.batch.additional.age.application.job.listener;

import com.batch.additional.age.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

/**
 * Stepの事前処理と事後処理実行クラス
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AgeStepListener implements StepExecutionListener {

    private final UserRepository userRepository;

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        log.info("ステップ開始 : {} , ステータス : {}", stepExecution.getStepName(), stepExecution.getStatus());
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("ステップ終了 : {} , ステータス : {}", stepExecution.getStepName(), stepExecution.getStatus());

        if (stepExecution.getStepName().equals("ageAdditionalStep")) {
            final int readCount = stepExecution.getReadCount();
            log.info("年齢更新処理 処理件数 : {}", readCount);

            // DBのデータをログに出力
            // h2のVMマシンシャットダウン時のデータ保持設定が上手く効かないのでデバッグ用に追加
            userRepository.stdoutAllUserData();

            // Line通知を行うために chunkの処理件数をJob ExecutionContextに保存する
            final ExecutionContext jobContext = stepExecution.getJobExecution().getExecutionContext();
            jobContext.put("ageAdditionalCount", readCount);
        }

        log.info("ExecutionContext : {}", stepExecution.getExecutionContext());
        log.info("Exit Status : {}", stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
