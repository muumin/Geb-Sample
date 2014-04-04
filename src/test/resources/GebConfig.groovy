import geb.report.ScreenshotReporter
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerDriverService

if (System.getProperty("os.name") ==~ /^Mac.*/) {
    System.setProperty('webdriver.chrome.driver', 'drivers/chrome/chromedriver')
} else if (System.getProperty("os.name") ==~ /^Windows.*/) {
    System.setProperty('webdriver.chrome.driver', 'drivers/chrome/chromedriver.exe')
}

// -Dgeb.build.reportsDir=geb-repo/htmlunit
//reportsDir = "geb-repo/htmlunit"

driver = {
    def driver = new HtmlUnitDriver(true)
    driver.getWebClient().getOptions().setThrowExceptionOnScriptError(false)
//    driver.getWebClient().getOptions().setThrowExceptionOnFailingStatusCode(true);
//    driver.getWebClient().getOptions().setCssEnabled(false);
//    driver.getWebClient().getOptions().setJavaScriptEnabled(true);
//    driver.getWebClient().getOptions().setTimeout(50000);
    driver
}

// vm option.
// -Dgeb.env=firefox
environments {
    htmlunit {
        driver = {
            def driver = new HtmlUnitDriver(true)
            driver.getWebClient().getOptions().setThrowExceptionOnScriptError(false)
            driver
        }
    }
    firefox {
        driver = { new FirefoxDriver() }
    }

    chrome {
        driver = { new ChromeDriver() }
    }
    ie {
        driver = {
            def ieCapabilities = DesiredCapabilities.internetExplorer()
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
            InternetExplorerDriverService.Builder ies = new InternetExplorerDriverService.Builder()
            ies.usingDriverExecutable(new File("drivers/ie/IEDriverServer.exe"))
            new InternetExplorerDriver(ies.build(), ieCapabilities)
        }
    }
}

// avoid report file name being garbled.
reporter = new ScreenshotReporter() {
    @Override
    protected escapeFileName(String name) {
        // name.replaceAll("[^\\w\\s-]", "_")
        name.replaceAll('[\\\\/:\\*?\\"<>\\|]', '_')
    }
}
