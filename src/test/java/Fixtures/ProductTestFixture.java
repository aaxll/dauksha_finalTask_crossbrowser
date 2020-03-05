package Fixtures;

import PageObjects.CartPage;
import PageObjects.ProductPage;
import PageObjects.WishlistPage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class ProductTestFixture extends TestBase{

    public ProductPage productPage;
    public CartPage cartPage = new CartPage();
    public WishlistPage wishlistPage = new WishlistPage();

    public void productTestsFixture(RemoteWebDriver driver){
        setWebDriver(driver);
        productPage = open("/?id_product=2&controller=product#/size-s/color-black", ProductPage.class);
    }
}
