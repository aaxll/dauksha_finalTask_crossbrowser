package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class ProductPage {

    @Step(value = "Add product to cart from its page")
    public void addProductToCartFromProductPage(){
        clickAddToCartBtn();
        $("span.cross").shouldBe(Condition.visible).click();
    }

    @Step(value = "Add additional custom product to cart")
    public void addCustomProductToCart(String productURL){
        open(productURL);
        addProductToCartFromProductPage();
        open("/?controller=order");

    }

    @Step(value = "Select size")
    public void selectSize(String size){
        $("#group_1").selectOptionContainingText(size);
        checkUrl("/size-"+size.toLowerCase());
    }

    @Step(value = "Select color")
    public void selectColor(String color){
        $("a.color_pick[name="+color+"]").click();
        checkUrl("/color-"+color.toLowerCase());
    }

    @Step(value = "Check URL")
    public void checkUrl(String param){
        assertThat(url(), containsString(param));
    }

    //document.getElementById("quantity_wanted").value

    @Step(value = "Increase product qty")
    public void increaseProductQty(){
        int defaultQty = Integer.parseInt((String) executeJavaScript("return document.getElementById(\"quantity_wanted\").value"));
        $("i.icon-plus").click();
        int currentQty = Integer.parseInt((String) executeJavaScript("return document.getElementById(\"quantity_wanted\").value"));
        checkQty(defaultQty+1, currentQty);

    }

    @Step(value = "Decrease product qty")
    public void decreaseProductQty(){
        int defaultQty = Integer.parseInt((String) executeJavaScript("return document.getElementById(\"quantity_wanted\").value"));
        $("i.icon-minus").click();
        int currentQty = Integer.parseInt((String) executeJavaScript("return document.getElementById(\"quantity_wanted\").value"));
        if (defaultQty == 1){
            checkQty(defaultQty, currentQty);
        }
        else {
            checkQty(defaultQty-1, currentQty);
        }
    }

    @Step(value = "Set product Qty value")
    public void setProductQty(String qty){
        $("#quantity_wanted").setValue(qty);
    }

    @Step(value = "Check product qty input highlight")
    public void checkInputHighlight(){
        $("#quantity_wanted").shouldHave(Condition.attribute("style", "border: 1px solid red;"));
    }

    @Step(value = "Check Qty")
    public void checkQty(int cur, int def){
        Assertions.assertEquals(def, cur);
    }

    @Step(value = "Click Add to cart button")
    public void clickAddToCartBtn(){
        $("button.exclusive").click();
    }

    @Step(value = "Check error message")
    public void checkErrorMessage(){
        $("p.fancybox-error").shouldBe(Condition.visible).shouldHave(Condition.text("Null quantity"));
    }

    @Step(value = "Click Display all pictures")
    public void clickDisplayAllPictures(){
        $("#wrapResetImages").click();
    }

    @Step(value = "Check pictures visibility")
    public void checkPicturesVisibility(){
        int size = $$("#thumbs_list_frame >li").size();
        for (int i = 0; i < size; i++){
            $$("#thumbs_list_frame >li").get(i).shouldBe(Condition.visible);
        }
    }

    @Step(value = "Click Add to wishlist")
    public void clickAddToWishlist(){
        LoginPage loginPage = new LoginPage();
        open("/?controller=authentication&back=my-account");
        loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        open("/?id_product=2&controller=product#/size-s/color-black");
        $("#wishlist_button").click();
    }

    @Step(value = "Check wishlist message")
    public void checkWishlistMessage(){
        $("p.fancybox-error").shouldBe(Condition.visible).shouldHave(Condition.text("Added to your wishlist"));
    }


}
