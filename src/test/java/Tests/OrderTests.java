package Tests;

import Fixtures.OrderTestsFixture;
import PageObjects.OrderPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Epic(value = "Order")
@Feature(value = "Order product features")
public class OrderTests extends OrderTestsFixture {

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[order-1] Order product with login")
    @Description(value = "Order product with authorised user")
    @Severity(SeverityLevel.NORMAL)
    public void orderProductWithLogin(RemoteWebDriver driver){
        setWebDriver(driver);
        loginPage.goToLoginPage();
        loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        OrderPage orderPage = cartPage.clickCheckoutBtn();
        orderPage.checkAddressStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkShippingStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkPaymentStep();
        orderPage.selectPaymentMethod();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkOrderConfirmation();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[order-1] Order product with no login")
    @Description(value = "Order product with not authorised user")
    @Severity(SeverityLevel.NORMAL)
    public void orderProductWithNoLogin(RemoteWebDriver driver){
        setWebDriver(driver);
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        OrderPage orderPage = cartPage.clickCheckoutBtn();
        orderPage.checkSignInStep();
        loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        orderPage.checkAddressStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkShippingStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkPaymentStep();
        orderPage.selectPaymentMethod();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkOrderConfirmation();
    }
}
