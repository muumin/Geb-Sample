Geb写経/動作確認
==================

yahooで複数回検索をするサンプル。

#起動方法

ブラウザを起動して画面のスナップショットをbuild/reports配下に保存します。

    gradlew ieTest
    gradlew firefoxTest
    gradlew chromeTest

# ドライバ更新

driversフォルダを削除して下さい。

#参考サイト

http://www.gebish.org/

https://github.com/geb/geb-example-gradle

http://hideoku.hatenablog.jp/

#TODO

IE立ち上がるようにはなったがタイムアウトエラーになります。
原因不明。

