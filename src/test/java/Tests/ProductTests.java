package Tests;

import Fixtures.ProductTestFixture;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.*;

@Epic(value = "Product")
@Feature(value = "Product page features")
public class ProductTests extends ProductTestFixture {

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)    
    @DisplayName(value = "[pp-1] Product page URL get parameters")
    @Description(value = "Check get parameters in product URL")
    @Severity(SeverityLevel.CRITICAL)
    public void getParametersInUrl(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.selectSize("L");
        productPage.selectColor("White");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[pp-2] Product Qty changing")
    @Description(value = "Product Qty must change after pressing -/+ icons")
    @Severity(SeverityLevel.CRITICAL)
    public void productQtyChanging(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.increaseProductQty();
        productPage.decreaseProductQty();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[pp-3] Qty input highlight")
    @Description(value = "Qty input should have red highlight if product Qty=0")
    @Severity(SeverityLevel.MINOR)
    public void productQtyHighlight(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.setProductQty("0");
        productPage.checkInputHighlight();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[pp-4] Add zero product qty to cart")
    @Description(value = "When you try to add a product with zero qty, an error message appears.")
    @Severity(SeverityLevel.MINOR)
    public void productZeroQtyToCart(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.setProductQty("0");
        productPage.clickAddToCartBtn();
        productPage.checkErrorMessage();
        cartPage.goToCartPage();
        cartPage.isProductExistInCart(false);
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[pp-5] Show all pictures anchor on product page")
    @Description(value = "All pictures from the product description are displayed by clicking “Display all pictures”")
    @Severity(SeverityLevel.MINOR)
    public void displayAllPictures(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.clickDisplayAllPictures();
        productPage.checkPicturesVisibility();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @ArgumentsSource(CustomProviders.RemoteWebDriverProvider.class)
    @DisplayName(value = "[pp-6] Add to wishlist feature")
    @Description(value = "The product must be added to the user's wish list")
    @Severity(SeverityLevel.NORMAL)
    public void addToWishList(RemoteWebDriver driver){
        productTestsFixture(driver);
        productPage.clickAddToWishlist();
        productPage.checkWishlistMessage();
        wishlistPage.goWishlistPage();
        wishlistPage.isProductExistInWishlist();

    }
}
