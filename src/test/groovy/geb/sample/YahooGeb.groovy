package geb.sample

import geb.spock.GebReportingSpec
import spock.lang.Unroll

class YahooGeb extends GebReportingSpec {

    @Unroll
    def "Yahoo で「#keywaord」を検索する"() {
        when:
        to YahooHomePage
        then:
        at YahooHomePage

        when:
        search.field.value keywaord
        report("検索キーワード入力")
        and:
        search.button.click()
        then:
        at YahooResultPage
        report("検索結果")
        and:
        firstResultLink.text() == firstLinkText

        when:
        firstResultLink.click()
        then:
        at resultPage

        where:
        keywaord    | firstLinkText                 | resultPage
        "wikipedia" | "ウィキペディア（Wikipedia）" | WikipediaPage
        "google"    | "Google"                      | GooglePage
        "goo"       | "goo"                         | GooPage
    }
}
