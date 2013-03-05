package geb.sample

import geb.Page

class GoogleHomePage extends Page {
    static url = "http://www.google.com"
    static at = { title == "Google"}
}
