package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static config.Credentials.credentials;

public class TestBase {

    @BeforeAll
    public static void setup() {
        final String login = credentials.login();
        final String password = credentials.password();
        final String remoteUrl = System.getProperty("selenoidUrl");
        final String selenoidUrl = String.format("https://%s:%s@%s", login, password, remoteUrl);

        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
//        Configuration.remote = String.format("https://%s:%s@%s/wd/hub/",
//                login, password, System.getProperty("selenoidUrl"));
        Configuration.remote = selenoidUrl + "/wd/hub/";
    }

    @AfterEach
    public void tearDown() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        closeWebDriver();

        Attach.addVideo(sessionId);
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
