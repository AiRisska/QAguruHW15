import com.codeborne.selenide.*;
import config.ConfigProject;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static java.lang.String.format;


public class TestBase {
    public static ConfigProject config = ConfigFactory.create(ConfigProject.class, System.getProperties());

    @BeforeAll
    static void setup() throws MalformedURLException {

        //для удаленного запуска тестов
        if (config.getRemote().equals("yes")) {
            System.out.println("Запуск на удаленных ресурсах");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", config.getBrowser());
            capabilities.setCapability("browserVersion", "");
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            String remoteUrl=config.remoteUrl();
            String remoteLogin=config.remoteLogin();
            String remotePassword=config.remotePassword();
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create(format("https://%s:%s@%s", remoteLogin, remotePassword, remoteUrl)).toURL(),
                    capabilities
            );
        } else {
            System.out.println("Запуск на локальных ресурсах");
        }

        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getSize();

    }

    @BeforeEach
    public void setProperty() {

        if (config.getEnvironment().equals("prod")) {
            System.out.println("Запуск на "+config.getEnvironment());
            System.setProperty("env", "prod");
        } else {
            System.out.println("Запуск на "+config.getEnvironment());
            System.setProperty("env", "test");
        }
    }

    @AfterEach
    public void shutDown() {
        Selenide.closeWebDriver();
    }
}