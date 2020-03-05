package CustomProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

public class LocalWebDriverProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        System.setProperty("webdriver.chrome.driver", "C:/testing/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:/testing/geckodriver-v0.26.0-win64/geckodriver.exe");
        return Stream.of(
                Arguments.of(new FirefoxDriver()),
                Arguments.of(new ChromeDriver())
        );
    }
}
