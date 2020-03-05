package PageObjects;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;


public class WishlistPage {

    @Step(value = "Go wishlist page")
    public void goWishlistPage(){
        open("/?fc=module&module=blockwishlist&controller=mywishlist");
    }

    @Step(value = "Check if product exist in wishlist")
    public void isProductExistInWishlist(){
        $("tr>td")
                .find(byText("My wishlist"))
                .click();
        $$("div.wlp_bought li").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
