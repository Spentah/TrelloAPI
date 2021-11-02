import api.objects.*;

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
        list.createList(board.getId(), "Backlog");
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
        checkItem.updateCheckItem(card.getId(), checkItem.getId("Понять протокол HTTP"), "complete");
        List newList = new List();
        newList.createList(board.getId(), "Done");
        newList.moveList(list.getId(), board.getId(), newList.getId());
        list.archiveList(list.getId(), true);
        checkItem.updateCheckItem(card.getId(), checkItem.getId("Выучить методы запросов"), "complete");
        Sticker sticker = new Sticker();
        sticker.createSticker(card.getId(), "thumbsup", 50, 50, 0);
    }

    @Test
    public void uiTest() {
        open("https://trello.com/");
        new LoginPage().clickSignUp()
                .inputInField(LoginPage.Fields.USER, System.getProperty("bibaEmail"))
                .sleep(3000)
                .submit()
                .inputInField(LoginPage.Fields.PASSWORD, System.getProperty("bibaPassword"))
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
