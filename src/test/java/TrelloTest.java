import api.TrelloApi;
import api.objects.*;

import api.utils.DatabaseExecutor;
import hooks.Hooks;
import org.testng.annotations.Test;
import ui.pages.BoardPage;
import ui.Colors;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TrelloTest extends Hooks {

    @Test
    public void apiTest() {
        TrelloApi trelloApi = new TrelloApi();
        trelloApi
                .createBoard("KanbanTool")
                .createList("Backlog")
                .createCard("Карточка для изучения API")
                .createAttachment("https://yt3.ggpht.com/a/AATXAJyTnQBEfTDrBLkJ-swLGJN9pZUNOetKeMh2iJao=s900-c-k-c0xffffffff-no-rj-mo")
                .createDescription("Тут будет отмечаться процесс обучения")
                .createDue()
                .createChecklist()
                .createCheckItem("Понять протокол HTTP")
                .createCheckItem("Выучить методы запросов")
                .updateCheckItem("Понять протокол HTTP")
                .createList("Done")
                .moveList("Backlog","Done")
                .archiveList()
                .updateCheckItem("Выучить методы запросов")
                .createSticker();
    }

    @Test
    public void uiTest() {
        open("https://trello.com/");
        new LoginPage().clickSignUp()
                .input("user", DatabaseExecutor.extract("login"))
                .sleep(3000)
                .submit()
                .input("password", DatabaseExecutor.extract("password"))
                .submit();
        new MainPage().clickBoardsList()
                .clickOnBoardByName("KanbanTool");
        new BoardPage().isCardInList("Done", "Карточка для изучения API")
                .clickOnCardByName("Карточка для изучения API")
                .isCheckboxSelected("Понять протокол HTTP")
                .isCheckboxSelected("Выучить методы запросов")
                .windowMenuClick()
                .chooseAndClickOnColor(Colors.GREEN)
                .activateDateCheckbox()
                .clickCloseButton()
                .renameBoard("KanbanTool", "Только для образования");
    }
}
