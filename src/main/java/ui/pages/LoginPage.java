package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    private SelenideElement submitButton = $x("//*[@type='submit']");
    private SelenideElement signupButton = $x("//a[@href='/login']");
//            $$(By.xpath("//a[contains(@class,'btn')] | //input[contains(@class,'button')]"));
    private SelenideElement userField = $(By.id("user"));
    private SelenideElement passwordField = $(By.id("password"));

    @Step("Нажимаем на кнопку \"Войти\"")
    public LoginPage clickSignUp() {
        signupButton.shouldBe(exist).click();
        return this;
    }

    @Step("Вводим в поле '{field}' значение")
    public LoginPage inputInField(LoginPage.Fields field, String value) {
        switch (field) {
            case USER : userField.setValue(value);
                        break;
            case PASSWORD : passwordField.setValue(value);
                        break;
        }
        return this;
    }

    @Step("Кликаем по кнопке \"Принять\"")
    public LoginPage submit() {
        submitButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажимаем на кнопку \"Логин\"")
    public LoginPage login() {
        $x("//input[contains(@class,'button')]").click();
        return this;
    }

    public LoginPage sleep(int timeInMillis) {
        Selenide.sleep(timeInMillis);
        return this;
    }

    public enum Fields {
        USER,
        PASSWORD;
    }


}
