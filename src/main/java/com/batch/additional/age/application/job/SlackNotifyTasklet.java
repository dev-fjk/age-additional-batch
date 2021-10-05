package com.batch.additional.age.application.job;

import com.batch.additional.age.domain.service.MsgService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
@RequiredArgsConstructor
public class SlackNotifyTasklet implements Tasklet {

    private final MsgService slackMsgService;

    /**
     * Slackに年齢加算結果の通知を行う
     *
     * @param contribution : コントリビューション
     * @param chunkContext : chunk情報
     * @return repeatStatus
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) {
        log.info("SlackNotifyTasklet Execute START");

        // 処理件数を取得
        final ExecutionContext executionContext = contribution.getStepExecution()
                .getJobExecution().getExecutionContext();

        // Lineに送信したのと同じメッセージを取得
        final String notifyMsg = (Objects.nonNull(executionContext.get("notifyMsg")))
                ? (String) executionContext.get("notifyMsg") : null;
        log.info("Slack通知メッセージ : {}", notifyMsg);

        slackMsgService.sendMessage(notifyMsg);

        log.info("SlackNotifyTasklet Execute END");
        return RepeatStatus.FINISHED;
    }
}
