package ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement boardsButton = $x("//a[contains(@href,'/boards')]");
    private List<SelenideElement> tools = $$x("//div[@class='board-tile-details-name']");
    private List<SelenideElement> boards = $$x("//div[@class='_1NHHI0mNN-7mZv']");

    public MainPage clickBoardsList() {
        boardsButton.shouldBe(exist, Duration.of(20, ChronoUnit.SECONDS)).click();
        return this;
    }

    public MainPage clickOnBoardByName(String boardName) {
//        $x("//div[@title='" + boardName + "']").shouldBe(visible).click();
        SelenideElement board = tools.stream().filter(SelenideElement::exists).filter(element -> element.getText().equals(boardName))
                .findFirst().orElseThrow(() -> new RuntimeException("Нет доски с таким именем"));
        board.click();
        return this;
    }


}
