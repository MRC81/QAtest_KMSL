import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static io.qameta.allure.Allure.step;
import static java.lang.System.setProperty;


@ExtendWith({TextReportExtension.class})
public class SetUp {

    @BeforeEach
    public void launchBrowserAndOpenSite() {
        // get the browser value from the system properties if it was specified via the gradle build task
        String browser = System.getProperty("selenide.browser");

        // in case the browser value wasn't specified set the 'default' value to get the Chrome
        if (browser == null) {
            browser = "default";
        }

        switch (browser) {
            case "firefox":
                Configuration.browser = "firefox";
                break;

            case "edge":
                Configuration.browser = "edge";
                break;

            case "chrome":

            default:
                Configuration.browser = "chrome";
        }

        String host = "https://www.kmslh.com/";

//        if (this.getClass().getSimpleName().equals("Fields_Tests")) {
//            host = "http://qa.cloudserver25.com/";
//        } else if (this.getClass().getSimpleName().equals("SelectBox_Tests")) {
//            host = "http://qa2.cloudserver25.com/";
//        }

        clearBrowserCache();
        WebDriverRunner.driver().clearCookies();

        // Configuration.browser = browser;
        Configuration.holdBrowserOpen = false;
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.baseUrl = host;
        Configuration.headless = false;
        //Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "reports";

        setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        step("Launch the " + browser.substring(0, 1).toUpperCase() + browser.substring(1)
                + " browser and open the " + host + " site", () ->
                Selenide.open(host));
    }


    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        WebDriverRunner.closeWebDriver();
    }


}
