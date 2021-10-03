package com.batch.additional.age.domain.repository;

/**
 * アカウントテーブル RepositoryIF
 */
public interface AccountRepository {

    /**
     * アカウントテーブルの全データをログで出力する
     */
    void stdoutAllAccountData();
}
