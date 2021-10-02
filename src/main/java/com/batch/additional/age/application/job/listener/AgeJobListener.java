package com.batch.additional.age.application.job.listener;

import com.batch.additional.age.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * ジョブの事前処理と事後処理を実行するクラス
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AgeJobListener implements JobExecutionListener {

    private final UserRepository userRepository;

    @Override
    public void beforeJob(final JobExecution jobExecution) {
        log.info("ジョブ開始 : {}, ステータス : {}", jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());
        log.info("ジョブ開始時刻 : {}", jobExecution.getStartTime());
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {

        // DBのデータをログに出力
        // h2のVMマシンシャットダウン時のデータ保持設定が上手く効かないのでデバッグ用に追加
        userRepository.stdoutAllUserData();

        log.info("ジョブ終了 : {}, ステータス : {}", jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());
        log.info("ジョブ終了時刻 : {}", jobExecution.getEndTime());
    }
}
