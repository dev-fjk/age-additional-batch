package com.batch.additional.age.infrastructure.repositoryimpl;

import com.batch.additional.age.domain.repository.UserRepository;
import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
import com.batch.additional.age.infrastructure.db.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public void stdoutAllUserData() {
        List<DbUserInfo> userList = userMapper.findAll();
        log.info("====== ユーザー情報一覧を表示します。 =========");
        userList.forEach((tmp) ->
                log.info("DBデータ : {}", tmp)
        );
        log.info("====== ユーザー情報一覧を表示しました。 =========");
    }
}
