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
    private SelenideElement kanbanTool = $x("//a[@title='KanbanTool']");
    private List<SelenideElement> boards = $$x("//div[@class='_1NHHI0mNN-7mZv']");

    public MainPage clickBoardsList() {
        boardsButton.shouldBe(exist, Duration.of(20, ChronoUnit.SECONDS)).click();
        return this;
    }

    public MainPage clickOnBoardByName(String boardName) {
        $x("//div[@title='" + boardName + "']").shouldBe(visible).click();
        return this;
    }


}
