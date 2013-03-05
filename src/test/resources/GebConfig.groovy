import geb.report.ScreenshotAndPageSourceReporter
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerDriverService

reportsDir = "geb-repo/htmlunit"
if (System.getProperty("os.name") ==~ /^Mac.*/) {
    System.setProperty('webdriver.chrome.driver', 'drivers/chrome/chromedriver')
} else if (System.getProperty("os.name") ==~ /^Windows.*/) {
    System.setProperty('webdriver.chrome.driver', 'drivers/chrome/chromedriver.exe')
}
driver = {
    def driver = new HtmlUnitDriver()
    driver.javascriptEnabled = true
    driver
}

// -Dgeb.env=firefox を指定された場合
environments {
    firefox {
        reportsDir = "geb-repo/firefox"
        driver = { new FirefoxDriver() }
    }

    chrome {
        reportsDir = "geb-repo/chrome"
        driver = { new ChromeDriver() }
    }
    ie {
        driver = {
            reportsDir = "geb-repo/ie"
            def ieCapabilities = DesiredCapabilities.internetExplorer()
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
            InternetExplorerDriverService.Builder ies = new InternetExplorerDriverService.Builder()
            ies.usingDriverExecutable(new File("drivers/ie/IEDriverServer.exe"))
            new InternetExplorerDriver(ies.build(), ieCapabilities)
        }
    }
}

// レポート日本語文字化け対応
reporter = new ScreenshotAndPageSourceReporter() {
    @Override
    protected escapeFileName(String name) {
        // name.replaceAll("[^\\w\\s-]", "_")
        name.replaceAll('[\\\\/:\\*?\\"<>\\|]', '_')
    }
}
