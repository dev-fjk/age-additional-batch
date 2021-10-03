-- postgre使用時のUSERテーブル投入データ
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '一郎', 20, CURRENT_DATE + cast('-24 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '次郎', 20, CURRENT_DATE + cast('-21 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '三郎', 20, CURRENT_DATE + cast('-19 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '四郎', 20, CURRENT_DATE + cast('-20 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '五郎', 20, CURRENT_DATE + cast('-25 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '六郎', 20, CURRENT_DATE + cast('-30 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '七郎', 20, CURRENT_DATE + cast('-50 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '八郎', 20, CURRENT_DATE + cast('-50 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '九郎', 20, CURRENT_DATE + cast('-1 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '十郎', 10, CURRENT_DATE + cast('-200 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');

-- 以下は対象外想定データ
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '十一郎', 20, CURRENT_DATE -1 + cast('-24 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');
INSERT INTO ACCOUNT(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER, DELETE_FLG)
values ('テスト', '十二郎', 20, CURRENT_DATE +1 + cast('-20 years' as INTERVAL), '年齢加算バッチ', '年齢加算バッチ', '0');