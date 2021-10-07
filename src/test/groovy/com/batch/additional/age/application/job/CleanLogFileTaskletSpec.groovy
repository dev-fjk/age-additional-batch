package com.batch.additional.age.application.job

import com.batch.additional.age.UnitTestBase
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import spock.lang.Shared
import spock.lang.Unroll

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

class CleanLogFileTaskletSpec extends UnitTestBase {

    CleanLogFileTasklet sut

    File logFilePath

    @Shared
    String mockLogFileDir = "src/test/resources/mock/log"

    StepContribution stepContribution
    ChunkContext chunkContext

    def setupSpec() {
        Path path = Paths.get(mockLogFileDir)
        if (!Files.exists(path)) {
            Files.createDirectory(path)
        }
    }

    def setup() {
        stepContribution = Mock(StepContribution)
        chunkContext = Mock(ChunkContext)
        fileSetup()
    }

    @Unroll
    def "ファイル削除処理_今日分と昨日分のログファイル以外は削除されるか"() {
        given:
        logFilePath = new File(mockLogFileDir)
        sut = new CleanLogFileTasklet(logFilePath)

        when:
        RepeatStatus result = sut.execute(stepContribution, chunkContext)

        then:
        noExceptionThrown()
        result == RepeatStatus.FINISHED

        // 一昨日分までのログが全て削除され、 今日と昨日分のファイルだけが残っているか。
        logFilePath.listFiles().length == 2
    }

    /**
     * 今日から二日前までの日付を持つログファイルを作成する
     */
    void fileSetup() {
        List<String> dateStrList = new ArrayList<>()
        dateStrList.add(LocalDate.now().format(this.dateFmt))
        dateStrList.add(LocalDate.now().minusDays(1).format(this.dateFmt))
        dateStrList.add(LocalDate.now().minusDays(2).format(this.dateFmt))

        for (String tmp : dateStrList) {
            Path path = Paths.get(mockLogFileDir + "/" + "batch_" + tmp + ".log")
            if (!Files.exists(path)) {
                Files.createFile(path)
            }
        }
    }
}
