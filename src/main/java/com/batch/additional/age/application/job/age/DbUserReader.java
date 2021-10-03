package com.batch.additional.age.application.job.age;

import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
import com.batch.additional.age.infrastructure.db.mapper.UserMapper;
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
 * ユーザー情報取得用ItemReader
 */
@Slf4j
@StepScope
@Component
public class DbUserReader extends MyBatisCursorItemReader<DbUserInfo> {

    /**
     * コンストラクタ
     * クエリ設定
     *
     * @param sqlSessionFactory : セッションファクトリ
     */
    public DbUserReader(final SqlSessionFactory sqlSessionFactory) {
        this.setSqlSessionFactory(sqlSessionFactory);
        this.setQueryId(UserMapper.class.getName() + ".fetchTodayBirthUser");
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
     * @return ユーザー情報
     * @throws Exception : 読み込み時例外
     */
    @Override
    protected DbUserInfo doRead() throws Exception {
        final DbUserInfo userInfo = super.doRead();
        log.info("読み込みデータ : {}", userInfo);
        return userInfo;
    }
}
