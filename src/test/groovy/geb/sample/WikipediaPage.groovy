package geb.sample

import geb.Page

class WikipediaPage extends Page {
    static at = { waitFor { title == 'Wikipedia' } } 
}
