package geb.sample

import geb.Page

class GooPage extends Page {
    static at = { waitFor {title == 'goo'} }
}
