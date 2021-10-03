package com.batch.additional.age.application.job.age;

import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
import com.batch.additional.age.infrastructure.db.mapper.UserMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * ユーザー情報更新用ItemWriter
 */
@Slf4j
@StepScope
@Component
public class DbUserWriter extends MyBatisBatchItemWriter<DbUserInfo> {

    /**
     * コンストラクタ sqlSessionFactoryと実行クエリを設定
     *
     * @param sqlSessionFactory : セッションファクトリ
     */
    public DbUserWriter(final SqlSessionFactory sqlSessionFactory) {
        this.setSqlSessionFactory(sqlSessionFactory);
        this.setStatementId(UserMapper.class.getName() + ".updateUserAge");

        // Readerで読みこんだデータが全て更新できなった際に異常とするか
        this.setAssertUpdates(false);
    }

    /**
     * DBにデータの書き込みを実施
     *
     * @param items : Readerで取得したデータ一覧
     */
    @Override
    public void write(List<? extends DbUserInfo> items) {
        super.write(items);
    }
}
