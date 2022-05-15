package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:environment/${env}.properties",
        "classpath:selenide.properties"
})
public interface ConfigProject extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("size")
    @DefaultValue("1920x1080")
    String getSize();

    @Key("env")
    @DefaultValue("test")
    String getEnvironment();

    @Key("tLogin")
    String getLogin();

    @Key("tPassword")
    String getPassword();

    @Key("tUrl")
    String getUrl();

    @Key("remote")
    @DefaultValue("no")
    String getRemote();

    @Key("selenide.url")
    String remoteUrl();

    @Key("selenide.login")
    String remoteLogin();

    @Key("selenide.password")
    String remotePassword();

}
