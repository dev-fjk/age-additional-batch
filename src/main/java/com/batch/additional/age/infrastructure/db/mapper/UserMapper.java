package com.batch.additional.age.infrastructure.db.mapper;

import com.batch.additional.age.infrastructure.db.dto.DbUserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * ユーザーテーブル Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * ユーザーテーブル全件取得
     *
     * @return 全ユーザ情報
     */
    List<DbUserInfo> findAll();
}
