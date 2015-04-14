import geb.report.ScreenshotReporter
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.ie.InternetExplorerDriver

def driverDir = System.getProperty("my.webdriver.dir")?System.getProperty("my.webdriver.dir"):"drivers"

// -Dgeb.build.reportsDir=geb-repo
if (!System.getProperty("geb.build.reportsDir")) {
    reportsDir = "geb-repo"
}

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
        driver = {
            def chromeDriver = "${driverDir}/chromedriver${System.getProperty("os.name") ==~ /^Windows.*/?".exe":""}"
            System.setProperty('webdriver.chrome.driver', chromeDriver)
            new ChromeDriver()
        }
    }
    ie {
        driver = {
            System.setProperty("webdriver.ie.driver", "${driverDir}/IEDriverServer.exe")
            def ieCapabilities = DesiredCapabilities.internetExplorer()
//            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
            ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true)
            new InternetExplorerDriver(ieCapabilities)
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
