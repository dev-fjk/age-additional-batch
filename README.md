# age-additional-batch
Spring Batchの備忘録 年齢更新バッチ  
Chunk/Taskletそれぞれ1Stepずつ作成する 

## 機能
- **Chunk** : 年齢更新機能 
  - **Reader** : DB(h2/Postgre) から 誕生日がバッチ実行日のユーザー一覧を取得しjavaオブジェクトへ変換
  - **Processor** : 現在日時からユーザーの誕生日を減算し 結果を年齢としてjavaオブジェクトに保存
  - **writer** : Processorで加工したデータをDBに書き込み
  
- **Tasklet** : Line通知機能 [JavaでLINEにメッセージを送信(通知)する手順【LINE APIで連携】](http://kakedashi-xx.com:25214/index.php/2021/07/01/post-2780/)
  - chunk正常終了後に実行 処理件数をLineBotで通知する

## 環境
~~~
- java 11
- Spring Boot
- Spring Batch
- Maven
- h2(localプロファイル)
- PostgreSQL(devプロファイル)
- My Batis
~~~

## TODO
後々JPAとJDBCでも同じChunk処理の処理作って備忘としておきたい
