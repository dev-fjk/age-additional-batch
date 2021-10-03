package com.batch.additional.age.infrastructure.repositoryimpl;

import com.batch.additional.age.domain.repository.AccountRepository;
import com.batch.additional.age.infrastructure.db.dto.DbAccountInfo;
import com.batch.additional.age.infrastructure.db.mapper.AccountMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * アカウントテーブル リポジトリ実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountMapper accountMapper;

    /**
     * 誕生日が本日のアカウント一覧をログに出力する
     */
    @Override
    public void stdoutAllAccountData() {
        List<DbAccountInfo> userList = accountMapper.findAll();
        log.info("====== アカウント情報一覧を表示します。 =========");
        userList.forEach((tmp) ->
                log.info("DBデータ : {}", tmp)
        );
        log.info("====== アカウント情報一覧を表示しました。 =========");
    }
}
