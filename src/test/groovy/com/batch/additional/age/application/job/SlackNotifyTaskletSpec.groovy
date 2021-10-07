package com.batch.additional.age.application.job

import com.batch.additional.age.UnitTestBase
import com.batch.additional.age.domain.service.MsgService
import org.spockframework.spring.SpringBean
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.repeat.RepeatStatus
import spock.lang.Shared
import spock.lang.Unroll

class SlackNotifyTaskletSpec extends UnitTestBase {

    SlackNotifyTasklet sut

    @SpringBean
    MsgService slackMsgService = Mock()

    @Shared
    String contextKey = "notifyMsg"

    // metadata
    JobExecution jobExecution
    StepExecution stepExecution
    StepContribution stepContribution
    ChunkContext chunkContext

    def setup() {
        jobExecution = new JobExecution(1)
        chunkContext = Mock(ChunkContext)
        slackMsgService = Mock()

        sut = new SlackNotifyTasklet(slackMsgService)
    }

    @Unroll
    def "Slack通知Tasklet_穴あけ"() {
        given:
        // executionContextのMapのvalueにnullを突っ込むと例外出るので試験パターンとして網羅はしない
        // 処理件数をMapで保持しているので作成
        Map<String, Object> params = new HashMap<>()
        params.put(contextKey, "テスト用のSlack通知メッセージです。")

        // JobRepository MetaData の設定
        jobExecution.setExecutionContext(new ExecutionContext(params))
        stepExecution = new StepExecution("ageAdditionalStep", jobExecution)
        stepContribution = new StepContribution(stepExecution)

        when:
        RepeatStatus result = sut.execute(stepContribution, chunkContext)

        then:
        noExceptionThrown()
        result == RepeatStatus.FINISHED
        1 * slackMsgService.sendMessage("テスト用のSlack通知メッセージです。")
    }
}
