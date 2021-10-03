package com.batch.additional.age.application.job.age;

import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Readerで読み取ったデータの年齢加工を行う
 */
@Slf4j
@StepScope
@Component
public class DbUserProcessor implements ItemProcessor<DbUserInfo, DbUserInfo> {


    /**
     * 年齢の加工を行う
     *
     * @param userInfo : ユーザー情報
     * @return : 年齢を更新したユーザー情報
     * @throws Exception : 加工時例外
     */
    @Override
    public DbUserInfo process(final DbUserInfo userInfo) throws Exception {

        final Integer oldAge = userInfo.getAge();

        // バッチ実行時の年と誕生日の年の差分を取得
        final Integer calculatedAge = LocalDate.now().getYear() - userInfo.getBirthDay().getYear();
        userInfo.setAge(calculatedAge);
        log.info("年齢を更新しました。 更新前 : {}, 更新後 : {}", oldAge, calculatedAge);

        return userInfo;
    }
}
