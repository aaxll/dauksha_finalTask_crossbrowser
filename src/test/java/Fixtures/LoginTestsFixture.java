package Fixtures;

import PageObjects.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class LoginTestsFixture extends TestBase{

    public LoginPage loginPage;


    public void loginTestsFixture(RemoteWebDriver driver){
        setWebDriver(driver);
        loginPage = open("/?controller=authentication&back=my-account", LoginPage.class);
    }

}
