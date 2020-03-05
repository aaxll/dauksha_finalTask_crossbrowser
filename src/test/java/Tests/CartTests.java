package Tests;

import Fixtures.CartTestsFixture;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.*;

@Epic(value = "Cart")
@Feature(value = "Basic cart functionality")
public class CartTests extends CartTestsFixture {

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-1] Add product to cart from product page")
    @Description(value = "Check adding product to cart from product page feature")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductFromProductPage(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.isProductExistInCart(true);
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-2] Delete product from cart")
    @Description(value = "Delete product from cart by trash button")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteProduct(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.isProductExistInCart(true);
        cartPage.deleteProductFromCart();
        cartPage.isProductExistInCart(false);
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-3] Check prices with single product in cart")
    @Description(value = "Only one product in cart")
    @Severity(SeverityLevel.CRITICAL)
    public void pricesWithSingleProduct(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.checkPrices();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-4] Check prices with multiple products in cart")
    @Description(value = "Multiple products in cart")
    @Severity(SeverityLevel.CRITICAL)
    public void pricesWithMultipleProducts(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.checkPrices();
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        cartPage.checkPrices();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-5] Check prices after changing qty by +/- icons")
    @Description(value = "Prices must be updated after qty changing")
    @Severity(SeverityLevel.CRITICAL)
    public void pricesAfterQtyChanging(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.checkPrices();
        cartPage.clickPlusIcon();
        cartPage.checkPrices();
        cartPage.clickMinusIcon();
        cartPage.checkPrices();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[cart-6] Product exists after press minus icon")
    @Description(value = "Check if product not removed after pressing minus icon")
    @Severity(SeverityLevel.CRITICAL)
    public void productNotExistsAfterMinusButton(RemoteWebDriver driver){
        cartTestsFixture(driver);
        cartPage.clickMinusIcon();
        sleep(500);
        cartPage.isProductExistInCart(false);

    }
}
