package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    @Step(value = "Go to login page")
    public void goToLoginPage(){
        open("/?controller=authentication&back=my-account");
    }

    @Step(value = "Login user with:")
    public MyAccountPage loginUser(String login, String password){
        $("#email").shouldBe(Condition.visible).setValue(login);
        $("#passwd").shouldBe(Condition.visible).setValue(password);
        $("#SubmitLogin").shouldBe(Condition.visible).click();
        return page(MyAccountPage.class);

    }

    @Step(value = "Check login error message")
    public void loginErrorMessageShouldHaveText(String errorMessage){
        $("#center_column > div.alert.alert-danger").shouldHave(Condition.text(errorMessage));
    }

    @Step(value = "Check email field highlight after page load")
    public void checkEmailHighlightAfterPageLoad(){
        $("#email").parent().shouldHave(Condition.attribute("class","form-group"));
    }

    @Step(value = "Check email field highlight after focus out")
    public void checkEmailHighlightAfterFocusOut(){
        $("#email").click();
        $("#passwd").click();
        $("#email").parent().shouldHave(Condition.attribute("class","form-group form-error"));
    }

    @Step(value = "Check email field highlight with valid email")
    public void checkEmailHighlightWidthValidEmail(){
        $("#email").setValue("demo@demo.com");
        $("#passwd").click();
        $("#email").parent().shouldHave(Condition.attribute("class","form-group form-ok"));
    }

    @Step(value = "Check email field highlight with invalid email")
    public void checkEmailHighlightWidthInvalidEmail(){
        $("#email").setValue("demo@democom");
        $("#passwd").click();
        $("#email").parent().shouldHave(Condition.attribute("class","form-group form-error"));
    }
}
