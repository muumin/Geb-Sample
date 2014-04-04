package geb.sample

import geb.Module

class YahooSearchModule extends Module {
    static content = {
        field { $('#srchtxt') }
        button(to: YahooResultPage) {
            $('#srchbtn')
        }
    }
}
