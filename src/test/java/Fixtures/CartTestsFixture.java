package Fixtures;

import PageObjects.CartPage;
import PageObjects.LoginPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class CartTestsFixture extends TestBase{

    public CartPage cartPage;
    public ProductPage productPage;

    public void cartTestsFixture(RemoteWebDriver driver){
        setWebDriver(driver);
        productPage = open("/?id_product=1&controller=product", ProductPage.class);
        productPage.addProductToCartFromProductPage();
        cartPage = open("/?controller=order", CartPage.class);
    }
}
