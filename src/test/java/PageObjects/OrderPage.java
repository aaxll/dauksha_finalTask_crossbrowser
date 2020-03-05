package PageObjects;

import Fixtures.OrderTestsFixture;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage extends OrderTestsFixture {

    @Step(value = "Check step 02. Sign in")
    public void checkSignInStep(){
        $("li.step_current.second").shouldBe(Condition.exist);
    }

    @Step(value = "Check step 03. Address")
    public void checkAddressStep(){
        $("li.step_current.third").shouldBe(Condition.exist);
        $("#address_delivery").shouldBe(Condition.exist);
        $("#address_invoice").shouldBe(Condition.exist);
    }

    @Step(value = "Click Proceed to checkout button")
    public void clickProceedToCheckoutBtn(){
        $("button i.icon-chevron-right").click();
    }

    @Step(value = "Check step 04. Shipping")
    public void checkShippingStep(){
        $("li.step_current.four").shouldBe(Condition.exist);
        $("#cgv").click();
    }

    @Step(value = "Check step 05. Payment")
    public void checkPaymentStep(){
        $("li.step_current.last").shouldBe(Condition.exist);
        $$("#cart_summary td.cart_product").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Step(value = "Select payment method")
    public void selectPaymentMethod(){
        $("a.bankwire").click();
    }

    @Step(value = "Check order confirmation")
    public void checkOrderConfirmation(){
        $("p.cheque-indent>strong.dark").shouldHave(Condition.text("Your order on My Store is complete"));
    }

}
