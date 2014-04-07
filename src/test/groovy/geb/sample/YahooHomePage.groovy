package geb.sample

import geb.Page

class YahooHomePage extends Page {
    static url = 'http://www.yahoo.co.jp/'
    static at = { waitFor { title == 'Yahoo! JAPAN' } }
    static content = {
        search { module YahooSearchModule }
    }
}
