package Tests;

import Fixtures.LoginTestsFixture;
import PageObjects.MyAccountPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.remote.RemoteWebDriver;


@Epic(value = "Login")
@Feature(value = "Basic user sign in functionality")
public class LoginTests extends LoginTestsFixture {

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-1] Login user with correct credentials")
    @Description(value = "Login user with correct credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginUserWithCorrectCredentials(RemoteWebDriver driver){
        loginTestsFixture(driver);
        MyAccountPage myAccountPage = loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        myAccountPage.successfulLoginMessageShouldBeVisible();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-2] Login user with invalid credentials:")
    @Description(value = "Login user with invalid credentials: invalid email, invalid pass, both")
    @Severity(SeverityLevel.BLOCKER)
    public void loginUserWithInvalidCredentials(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser( "fakeuser1@gmail.com", "Userpass");
        loginPage.loginErrorMessageShouldHaveText("Authentication failed");
        loginPage.loginUser( "fakeuser@gmail.com", "Userpass1");
        loginPage.loginErrorMessageShouldHaveText("Authentication failed");
        loginPage.loginUser( "fakeuser1@gmail.com", "Userpass1");
        loginPage.loginErrorMessageShouldHaveText("Authentication failed");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-3] Login user with no email")
    @Description(value = "Email field is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoEmail(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser("", "userpass");
        loginPage.loginErrorMessageShouldHaveText("An email address required");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-4] Login user with no password")
    @Description(value = "Password field is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoPassword(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser("fakeuser@gmail.com", "");
        loginPage.loginErrorMessageShouldHaveText("Password is required");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-5] Login user with no credentials")
    @Description(value = "Email and password fields are empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoCredentials(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser("", "");
        loginPage.loginErrorMessageShouldHaveText("An email address required");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-6] Login user with invalid email format")
    @Description(value = "Try to login user with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    public void loginUserWithInvalidEmailFormat(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser("fakeusergmail.com", "Userpass");
        loginPage.loginErrorMessageShouldHaveText("Invalid email address");
        loginPage.loginUser("fakeuser@gmailcom", "Userpass");
        loginPage.loginErrorMessageShouldHaveText("Invalid email address");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-7] Login user with invalid password format")
    @Description(value = "Try to login user with invalid password format: short password")
    @Severity(SeverityLevel.NORMAL)
    public void loginUserWithInvalidPasswordFormat(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.loginUser("fakeuser@gmail.com", "user");
        loginPage.loginErrorMessageShouldHaveText("Invalid password");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-9] Email field highlight")
    @Description(value = "Check email field highlight in different conditions")
    @Severity(SeverityLevel.MINOR)
    public void emailFieldHighlight(RemoteWebDriver driver){
        loginTestsFixture(driver);
        loginPage.checkEmailHighlightAfterPageLoad();
        loginPage.checkEmailHighlightAfterFocusOut();
        loginPage.checkEmailHighlightWidthValidEmail();
        loginPage.checkEmailHighlightWidthInvalidEmail();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[login-10] Logout user")
    @Description(value = "User logout feature")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutUser(RemoteWebDriver driver){
        loginTestsFixture(driver);
        MyAccountPage myAccountPage = loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        myAccountPage.userLogOut();
    }
}
