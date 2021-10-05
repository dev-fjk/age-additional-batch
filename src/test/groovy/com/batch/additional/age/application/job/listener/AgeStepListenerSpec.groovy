package com.batch.additional.age.application.job.listener

import com.batch.additional.age.UnitTestBase
import com.batch.additional.age.domain.repository.AccountRepository
import org.spockframework.spring.SpringBean
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.test.MetaDataInstanceFactory
import spock.lang.Unroll

class AgeStepListenerSpec extends UnitTestBase {

    AgeStepListener sut

    @SpringBean
    AccountRepository accountRepository = Mock()

    JobExecution jobExecution
    StepExecution stepExecution

    void setup() {
        accountRepository = Mock()
        jobExecution = MetaDataInstanceFactory.createJobExecution()

        sut = new AgeStepListener(accountRepository)
    }

    @Unroll
    def "Step事前事後処理_Step名での分岐確認試験"() {
        given:
        stepExecution = new StepExecution(inStepName, jobExecution)
        stepExecution.setReadCount(10)
        when:
        sut.beforeStep(stepExecution)
        def result = sut.afterStep(stepExecution)

        then:
        noExceptionThrown()

        if (inStepName == "ageAdditionalStep") {
            // 年齢加算ステップの場合は DBのログ出力メソッドが軌道されていること。
            1 * accountRepository.stdoutAllAccountData()
        } else {
            0 * accountRepository.stdoutAllAccountData()
        }

        where:
        testName           | inStepName
        "ステップ名が年齢加算ステップ"   | "ageAdditionalStep"
        "ステップ名が年齢加算ステップ以外" | "cleanLogFileStep"
    }
}
