package com.batch.additional.age.infrastructure.db.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * アカウントテーブルEntity
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DbAccountInfo {

    private Integer accountId;
    private String lastName;
    private String firstName;
    private Integer age;
    private LocalDate birthDay;
    private LocalDate createdAt;
    private String createUser;
    private LocalDate updateAt;
    private String updateUser;
    private String deleteFlg;

    public void setAge(final Integer age) {
        this.age = age;
    }
}
