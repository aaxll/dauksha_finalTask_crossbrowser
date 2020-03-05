package CustomProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.stream.Stream;

public class RemoteWebDriverProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {

        FirefoxOptions firefox = new FirefoxOptions();
        firefox.setCapability("enableVNC", true);
        firefox.setCapability("enableVideo", false);
        firefox.setHeadless(false);
        firefox.addArguments("--width=1920");
        firefox.addArguments("--height=1080");

        ChromeOptions chrome = new ChromeOptions();
        chrome.setCapability("enableVNC", true);
        chrome.setCapability("enableVideo", false);
        chrome.setHeadless(false);
        chrome.addArguments("--window-size=1920,1080");

        return Stream.of(
                Arguments.of(new RemoteWebDriver(new URL("http://192.168.100.100:4444/wd/hub"), firefox)),
                Arguments.of(new RemoteWebDriver(new URL("http://192.168.100.100:4444/wd/hub"), chrome))
        );
    }
}
