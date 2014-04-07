package geb.sample

import geb.Page

class GooglePage extends Page {
    static at = { waitFor { title == 'Google'} }
}
