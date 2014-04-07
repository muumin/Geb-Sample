package geb.sample

import geb.Page

class YahooResultPage extends Page {
    static at = { waitFor {title.endsWith 'Yahoo!検索'} }
    static content = {
        // 検索部分
        search { module YahooSearchModule, buttonValue: '検索' }

        // 検索結果
        results(wait: true) { $("#WS2m div.hd") }
        // 検索結果リンク部分
        resultLink { i -> results[i].find("a") }
        // 最初の検索結果のリンク部分
        firstResultLink { resultLink(0) }
    }
}
