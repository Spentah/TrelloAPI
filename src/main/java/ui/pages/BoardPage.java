package ui.pages;

import api.objects.Board;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import org.testng.reporters.jq.Main;
import ui.Colors;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BoardPage {
    private Logger logger = Logger.getLogger(BoardPage.class);
    private SelenideElement cardWindow = $x("//div[contains(@class,'card-detail-window')]");
    private SelenideElement board = $(By.id("board"));
    private SelenideElement dateCheckbox = Selenide.$x("//a[contains(@class,'due-date-complete')]");
    private List<SelenideElement> cards = Selenide.$$x("//div[contains(@class,'list-cards')]");
    private List<SelenideElement> listHeaders = Selenide.$$x("//div[contains(@class,'list-header js-list')]");
    private List<SelenideElement> checkedCheckboxes = Selenide.$$x("//div[contains(@class,'item-state-complete')]");
    private SelenideElement closeButton = Selenide.$x("//a[contains(@class,'icon-close') and not (contains(@class,'icon-lg'))]");
    private SelenideElement windowMenu = Selenide.$x("//a[contains(@class,'window-cover-menu')]");
    private List<SelenideElement> headerButtons = $$x("//div[contains(@class,'board-header-btn')]");

    @Step("Кликаем по крточке с названием '{name}'")
    public BoardPage clickOnCardByName(String name) {
        if (cards.stream().anyMatch(e -> e.getText().contains(name))) {
            cards.stream().filter(e -> e.getText().contains(name))
                    .findFirst().get().shouldBe(exist).click();
            logger.info("Кликнули по карточке " + name);
        } else {
            Assert.fail("На доске нет карточки с названием " + name);
        }
        return this;
    }

    @Step("Убеждаемся, что карточка '{cardName}' находится в колонке '{listName}'")
    public BoardPage isCardInList(String listName, String cardName) {
        board.shouldBe(appear);
        SelenideElement listHeader = listHeaders.stream()
                .filter(e -> e.shouldBe(appear, Duration.of(10, ChronoUnit.SECONDS)).getText().equals(listName))
                .findFirst().get();
        Assert.assertTrue(listHeader.isDisplayed(), "Не найдена колонка " + listName);
        Assert.assertTrue(listHeader.sibling(0).getText().contains(cardName),
                "В колонке с названием " + listName + " не содержится карточка с названием " + cardName);
        return this;
    }

    @Step("Убеждаемся, чекбокс '{name}' активирован")
    public BoardPage isCheckboxSelected(String name) {
        cardWindow.shouldBe(appear);
        Assert.assertTrue(
        checkedCheckboxes.stream().filter(e -> e.shouldBe(exist, Duration.of(10, ChronoUnit.SECONDS)).shouldBe(visible).getText().equals(name))
                .findFirst().isPresent(),
                "Чекбокс " + name + " не активирован");
        return this;
    }

    @Step("Закрываем карточку")
    public BoardPage clickCloseButton() {
        closeButton.shouldBe(exist).click();
        return this;
    }

    @Step("Кликаем по меню карточки")
    public BoardPage windowMenuClick() {
        windowMenu.shouldBe(exist).click();
        return this;
    }

    @Step("Выбираем цвет обложки '{color}'")
    public BoardPage chooseAndClickOnColor(Colors color) {
        switch (color) {
            case GREEN : color.GREEN.getColor().shouldBe(exist).click();
                break;
            case YELLOW : color.YELLOW.getColor().shouldBe(exist).click();
                break;
            case ORANGE : color.ORANGE.getColor().shouldBe(exist).click();
                break;
            case RED : color.RED.getColor().shouldBe(exist).click();
                break;
            case PURPLE : color.PURPLE.getColor().shouldBe(exist).click();
                break;
            case DARK_BLUE : color.DARK_BLUE.getColor().shouldBe(exist).click();
                break;
            case BLUE : color.BLUE.getColor().shouldBe(exist).click();
                break;
            case SALAT : color.SALAT.getColor().shouldBe(exist).click();
                break;
            case PINK : color.PINK.getColor().shouldBe(exist).click();
                break;
            case BLACK : color.BLACK.getColor().shouldBe(exist).click();
                break;
            default :
                Logger.getLogger(Main.class).error("Выбранный цвет не присутсвует на форме.");
        }
        return this;
    }

    @Step("Активируем чекбокс даты")
    public BoardPage activateDateCheckbox() {
        if (dateCheckbox.parent().getAttribute("class").contains("is-due-complete")) {
            Logger.getLogger(BoardPage.class).error("Чекбокс даты уже активирован");
            return this;
        }
        dateCheckbox.shouldBe(exist).click();
        return this;
    }

    @Step("Меняем имя доски с '{oldName}' на '{newName}'")
    public BoardPage renameBoard(String oldName, String newName) {
        SelenideElement inputField = headerButtons.stream().filter(SelenideElement::isDisplayed).filter(element -> element.getText().equals(oldName))
                .findFirst().orElseThrow(() -> new RuntimeException("Нет поля с таким названием"));
        new Actions(inputField.getWrappedDriver()).moveToElement(inputField).click()
                .sendKeys(Keys.BACK_SPACE, newName, Keys.ENTER).build().perform();
        Board.updateName(newName, oldName);
        return this;
    }
}

