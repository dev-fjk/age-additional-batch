package com.batch.additional.age.application.job;

import com.batch.additional.age.domain.service.MsgService;
import io.netty.handler.codec.string.LineSeparator;
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

/**
 * 年齢更新結果のLine通知を行う
 */
@Slf4j
@StepScope
@Component
@RequiredArgsConstructor
public class LineNotifyTasklet implements Tasklet {

    private final MsgService lineMsgService;

    /**
     * ラインに通知するメッセージを作成し、 LineNotifyで通知を行う
     *
     * @param contribution : コントリビューション
     * @param chunkContext : chunk情報
     * @return repeatStatus
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) {
        log.info("LineNotifyTasklet Execute START");

        // 処理件数を取得
        final ExecutionContext executionContext = contribution.getStepExecution()
                .getJobExecution().getExecutionContext();

        final Integer readCount = (Objects.nonNull(executionContext.get("ageAdditionalCount")))
                ? (Integer) executionContext.get("ageAdditionalCount") : null;

        final StringBuilder notifyMsgBuilder = new StringBuilder();
        notifyMsgBuilder.append(LineSeparator.UNIX.value());
        notifyMsgBuilder.append("年齢加算バッチの処理が終了しました。");
        notifyMsgBuilder.append(LineSeparator.UNIX.value());
        notifyMsgBuilder.append("処理件数 : ").append(readCount).append(" 件");
        log.info("ライン通知メッセージ : {}", notifyMsgBuilder);

        final String notifyMsg = notifyMsgBuilder.toString();

        // slackにも同じメッセージを送信するのでjobExecutionContextに保存
        executionContext.put("notifyMsg", notifyMsg);

        // Lineへメッセージを送信する
        lineMsgService.sendMessage(notifyMsgBuilder.toString());

        log.info("LineNotifyTasklet Execute END");
        return RepeatStatus.FINISHED;
    }
}
