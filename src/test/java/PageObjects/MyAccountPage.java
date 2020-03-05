package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MyAccountPage {

    @Step(value = "Check successful login message")
    public void successfulLoginMessageShouldBeVisible(){
        $("p.info-account").shouldBe(Condition.visible);
    }

    @Step(value = "Check user logout feature")
    public void userLogOut(){
        $("a.logout").click();
        $("a.login").shouldBe((Condition.visible));
    }
}
