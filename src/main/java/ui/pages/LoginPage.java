package ui.pages;

import com.codeborne.selenide.Condition;
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
    private List<SelenideElement> inputFields = $$x("//input");
    private SelenideElement userField = $(By.id("user"));
    private SelenideElement passwordField = $(By.id("password"));

    @Step("Нажимаем на кнопку \"Войти\"")
    public LoginPage clickSignUp() {
        signupButton.shouldBe(exist).click();
        return this;
    }

    @Step("Вводим в поле '{name}' значение")
    public LoginPage input(String name, String value) {
        inputFields.stream()
                .filter(SelenideElement::isDisplayed)
                .filter(e -> e.shouldBe(exist).getAttribute("name").equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Нет поля " + name))
                .setValue(value);
        return this;
    }

    @Step("Кликаем по кнопке \"Принять\"")
    public LoginPage submit() {
        submitButton.shouldBe(visible).click();
        return this;
    }

//    @Step("Нажимаем на кнопку \"Логин\"")
//    public LoginPage login() {
//        $x("//input[contains(@class,'button')]").click();
//        return this;
//    }

    public LoginPage sleep(int timeInMillis) {
        Selenide.sleep(timeInMillis);
        return this;
    }

}
