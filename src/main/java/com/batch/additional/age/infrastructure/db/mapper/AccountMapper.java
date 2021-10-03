package com.batch.additional.age.infrastructure.db.mapper;

import com.batch.additional.age.infrastructure.db.dto.DbAccountInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * アカウントテーブル Mapper
 */
@Mapper
public interface AccountMapper {

    /**
     * アカウントテーブル全件取得
     *
     * @return 全アカウント情報
     */
    List<DbAccountInfo> findAll();
}
