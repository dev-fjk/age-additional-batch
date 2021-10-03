package com.batch.additional.age.application.job.age;

import com.batch.additional.age.infrastructure.db.dto.DbAccountInfo;
import com.batch.additional.age.infrastructure.db.mapper.AccountMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * アカウント情報取得用ItemReader
 */
@Slf4j
@StepScope
@Component
public class DbAccountReader extends MyBatisCursorItemReader<DbAccountInfo> {

    /**
     * コンストラクタ
     * クエリ設定
     *
     * @param sqlSessionFactory : セッションファクトリ
     */
    public DbAccountReader(final SqlSessionFactory sqlSessionFactory) {
        this.setSqlSessionFactory(sqlSessionFactory);
        this.setQueryId(AccountMapper.class.getName() + ".fetchTodayBirthAccount");
    }

    /**
     * コンストラクター実行直後に実行
     * クエリパラメータを設定
     */
    @PostConstruct
    public void postConstruct() {
        // 今日の日付と月日が一致するデータを対象に取得する
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMdd");
        final String todayStr = dtf.format(LocalDate.now());
        log.info("今日の日付 : {}", todayStr);

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("today", todayStr);
        this.setParameterValues(queryParam);
    }

    /**
     * DBからデータの読み取りを実施
     *
     * @return アカウント情報
     * @throws Exception : 読み込み時例外
     */
    @Override
    protected DbAccountInfo doRead() throws Exception {
        final DbAccountInfo accountInfo = super.doRead();
        log.info("読み込みデータ : {}", accountInfo);
        return accountInfo;
    }
}
