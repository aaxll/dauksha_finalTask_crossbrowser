package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

public class CartPage {

    private By qty = cssSelector("td.cart_quantity>input[type=\"hidden\"]");

    @Step(value = "Go to cart page")
    public void goToCartPage(){
        open("/?controller=order");
    }

    @Step(value = "Check if product exist in cart")
    public void isProductExistInCart(boolean condition){
        if (condition)
        {
            $("td.cart_product").shouldBe(Condition.visible);
        }
        else if(!condition)
        {
            $("td.cart_product").shouldNotBe(Condition.exist);
        }
    }

    @Step(value = "Delete product from cart by trash button")
    public void deleteProductFromCart(){
        $("i.icon-trash").click();
    }

    @Step(value = "Check product prices logic")
    public void checkPrices(){
        SelenideElement oneProduct;
        float totalShippingPrice;
        float totalTaxPrice;
        float totalPriceWithShipping = 0;
        float totalPriceWithShippingAndTax = 0;
        float oneProductPrice;
        float productQty;
        float oneProductTotalPrice;
        float totalPriceWithoutShippingAndTax = 0;

        totalShippingPrice = priceToFloat($("#total_shipping"));
        totalTaxPrice = priceToFloat($("#total_tax"));

        ElementsCollection allProductsInCart = $$("tr.cart_item");
        for (int i = 0; i < allProductsInCart.size(); i++) {
            oneProduct = allProductsInCart.get(i);
            oneProductPrice = priceToFloat(oneProduct.$("td.cart_unit>span.price>span.price"));
            System.out.println(oneProduct.$("input.cart_quantity_input").getText());
            productQty = Float.parseFloat(oneProduct.$(qty).getAttribute("value"));
            oneProductTotalPrice = oneProductPrice*productQty;
            step("Check total price for each product in cart (productPrice*Qty)");
            oneProduct.$("td.cart_total>span.price").shouldBe(Condition.text("$"+oneProductTotalPrice));
            totalPriceWithoutShippingAndTax = totalPriceWithoutShippingAndTax + oneProductTotalPrice;
            totalPriceWithShipping = totalPriceWithoutShippingAndTax + totalShippingPrice;
            totalPriceWithShippingAndTax = totalPriceWithShipping + totalTaxPrice;
        }
        step("Check total price for all products in cart without shipping and tax");
        $("#total_product").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithoutShippingAndTax)));
        step("Check total price for all products in cart with shipping but tax");
        $("#total_price_without_tax").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithShipping)));
        step("Check total price for all products in cart with shipping and tax");
        $("#total_price").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithShippingAndTax)));
    }

    @Step(value = "Click plus icon to increase qty of products in cart")
    public void clickPlusIcon(){
        String currentValue = $(qty).getAttribute("value");
        int currentValueInt = Integer.parseInt(currentValue);
        $("i.icon-plus").click();
        $(qty).shouldHave(Condition.attribute("value", String.valueOf(currentValueInt+1)));
        }

    @Step(value = "Click minus icon to decrease qty of products in cart")
    public void clickMinusIcon(){
        String currentValue = $(qty).getAttribute("value");
        int currentValueInt = Integer.parseInt(currentValue);
        $("i.icon-minus").click();
        if (currentValueInt == 1){
            //$(qty).shouldHave(Condition.attribute("value", String.valueOf(currentValueInt)));
        }
        else {
            $(qty).shouldHave(Condition.attribute("value", String.valueOf(currentValueInt - 1)));
        }
    }

    @Step(value = "Click checkout button")
    public OrderPage clickCheckoutBtn(){
        $("a.button.btn.btn-default.standard-checkout").click();
        return page(OrderPage.class);
    }

    public float roundPrice(float price){
        return (float) (Math.round(price * 100.0) / 100.0);
    }

    public float priceToFloat(SelenideElement selenideElement){
       String str = selenideElement.getText().replaceAll("[^0-9\\.]", "");
       return Float.parseFloat(str);
    }

    @Step("{stepName}")
    public void step(String stepName)
    {
    }
}
