# age-additional-batch
Spring Batchの備忘録 年齢更新バッチ  
Chunk/Taskletそれぞれ1Stepずつ作成する
## 環境
- java 11
- groovy 3.0
- Spring Boot 2.5.4
- Spring Batch
- Spock
- Maven
- h2(localプロファイル)
- PostgreSQL(devプロファイル)
- My Batis
## 機能
- **Chunk** : 年齢更新機能 
  - **Reader** : DB(h2/Postgre) から 誕生日がバッチ実行日のユーザー一覧を取得しjavaオブジェクトへ変換
  - **Processor** : 現在日時からユーザーの誕生日を減算し 結果を年齢としてjavaオブジェクトに保存
  - **writer** : Processorで加工したデータをDBに書き込み
- **Tasklet** : Line通知機能
  - [JavaでLINEにメッセージを送信(通知)する手順【LINE APIで連携】](http://kakedashi-xx.com:25214/index.php/2021/07/01/post-2780/)
  - 処理件数をLineBotでLINEに通知する
- **Tasklet** : ログファイル削除機能
  - ローカルのログディレクトリからログファイルを削除する(デフォルトは前日分までのファイルを保持)
