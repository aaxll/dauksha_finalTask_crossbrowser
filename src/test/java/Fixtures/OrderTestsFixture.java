package Fixtures;

import PageObjects.CartPage;
import PageObjects.LoginPage;
import PageObjects.ProductPage;
import Utils.AllureUtils;
import org.junit.jupiter.api.AfterEach;

public class OrderTestsFixture extends TestBase{
    public LoginPage loginPage = new LoginPage();
    public ProductPage productPage = new ProductPage();
    public CartPage cartPage = new CartPage();
    public AllureUtils allureUtils = new AllureUtils();

    @AfterEach
    public void allureEnv(){
        allureUtils.setAllureEnvironmentProp();
    }
}
