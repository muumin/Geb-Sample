package geb.sample

import geb.spock.GebReportingSpec

class GoogleGeb extends GebReportingSpec {

    def "Google ホームページに遷移する"() {
        given:
        to GoogleHomePage

        expect:
        at GoogleHomePage
    }
}
