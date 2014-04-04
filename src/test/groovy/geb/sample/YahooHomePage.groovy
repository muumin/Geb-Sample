package geb.sample

import geb.Page

class YahooHomePage extends Page {
    static url = 'http://www.yahoo.co.jp/'
    static at = { title == 'Yahoo! JAPAN' }
    static content = {
        // 部品化された検索部分
        // ボタンの値が違うのでそこだけ変更している
        search { module YahooSearchModule }
    }
}
