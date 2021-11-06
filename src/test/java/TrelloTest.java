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
        Board board = new Board();
        board.createBoard("KanbanTool");
        List list = new List();
        list.createList(Board.getIdByName("KanbanTool"), "Backlog");
        Card card = new Card();
        card.createCard(list.getId(), "Карточка для изучения API");
        Attachment attachment = new Attachment();
        attachment.createAttachment(card.getId(), "https://yt3.ggpht.com/a/AATXAJyTnQBEfTDrBLkJ-swLGJN9pZUNOetKeMh2iJao=s900-c-k-c0xffffffff-no-rj-mo");
        Description description = new Description();
        description.addDescription(card.getId(), "Тут будет отмечаться прогресс обучения");
        Due due = new Due();
        due.createDue(card.getId());
        Checklist checklist = new Checklist();
        checklist.createChecklist(card.getId());
        CheckItem checkItem = new CheckItem();
        checkItem.createCheckItem(checklist.getId(), "Понять протокол HTTP");
        checkItem.createCheckItem(checklist.getId(), "Выучить методы запросов");
        checkItem.updateCheckItem(card.getId(), "Понять протокол HTTP", "complete");
        List newList = new List();
        newList.createList(Board.getIdByName("KanbanTool"), "Done");
        newList.moveList(list.getId(), Board.getIdByName("KanbanTool"), newList.getId());
        list.archiveList(list.getId(), true);
        checkItem.updateCheckItem(card.getId(), "Выучить методы запросов", "complete");
        Sticker sticker = new Sticker();
        sticker.createSticker(card.getId(), "thumbsup", 50, 50, 0);
        Board b = new Board();
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
