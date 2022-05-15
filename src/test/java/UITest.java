import config.ConfigProject;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UITest extends TestBase{

    private final ConfigProject config = ConfigFactory.create(ConfigProject.class, System.getProperties());

    @Test
    @DisplayName("Одиночный тест")
    void testErrorUser() {

        open(config.getUrl() + "login.jsp");
        $("#main").shouldHave(text("SimbirSoft Jira"));
        $("#login-form-username").val(config.getLogin());
        $("#login-form-password").click();
        $("#login-form-password").val(config.getPassword());
        $("#login-form-submit").click();
        $("#announcement-banner").shouldNotBe(visible);
    }

    @Test
    @DisplayName("remote test")
    void testRemote(){
        open("https://yandex.ru/");
        
    }
}
