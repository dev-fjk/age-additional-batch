-- H2使用時のUSERテーブル投入データ
-- intervalが使えないようなので DATEADD('加算したい要素',加算量,加算対象)
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','一郎',20,DATEADD('yyyy',-24,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','次郎',20,DATEADD('yyyy',-21,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','三郎',20,DATEADD('yyyy',-19,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','四郎',20,DATEADD('yyyy',-20,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','五郎',20,DATEADD('yyyy',-25,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','六郎',20,DATEADD('yyyy',-30,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','七郎',20,DATEADD('yyyy',-50,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','八郎',20,DATEADD('yyyy',-100,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','九郎',20,DATEADD('yyyy',-1,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','十郎',10,DATEADD('yyyy',-200,CURRENT_DATE) ,'年齢加算バッチ','年齢加算バッチ','0');

-- 以下は対象外想定データ
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','十一郎',20,DATEADD('yyyy',-24,CURRENT_DATE -1) ,'年齢加算バッチ','年齢加算バッチ','0');
insert into USER(LAST_NAME, FIRST_NAME, AGE, BIRTH_DAY, CREATE_USER, UPDATE_USER,DELETE_FLG)
values ('テスト','十二郎',20,DATEADD('yyyy',-24,CURRENT_DATE + 1) ,'年齢加算バッチ','年齢加算バッチ','0');