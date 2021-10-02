package com.batch.additional.age.domain.repository;

/**
 * Userテーブル RepositoryIF
 */
public interface UserRepository {

    /**
     * ユーザーテーブルの全データをログで出力する
     */
    void stdoutAllUserData();
}
