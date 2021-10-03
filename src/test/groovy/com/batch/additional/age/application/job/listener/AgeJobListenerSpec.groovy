package com.batch.additional.age.application.job.listener

import com.batch.additional.age.UnitTestBase
import org.springframework.batch.core.JobExecution
import org.springframework.batch.test.MetaDataInstanceFactory
import spock.lang.Unroll

import java.time.LocalDate

class AgeJobListenerSpec extends UnitTestBase {

    AgeJobListener sut

    JobExecution jobExecution

    def setup() {
        jobExecution = MetaDataInstanceFactory.createJobExecution()

        // UnitTestBaseで指定した時刻のLocalDateを取得してDate型へ変換
        LocalDate localDate = LocalDate.now(this.mockClock())
        jobExecution.setStartTime(Date.from(localDate.atStartOfDay(this.zoneId).toInstant()))
        sut = new AgeJobListener()
    }

    @Unroll
    def "JobListener_穴あけ"() {
        when:
        sut.beforeJob(jobExecution)
        sut.afterJob(jobExecution)
        then:
        noExceptionThrown()
    }
}
