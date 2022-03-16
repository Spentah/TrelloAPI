import api.utils.DatabaseExecutor;
import org.testng.annotations.Test;
import ui.pages.BoardPage;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TestTrelloUi {

    @Test(priority = 2, groups = "ui")
    public void uiTest() {
        open("https://trello.com/");
        new LoginPage()
                .clickSignUp()
                .sleep(3000)
                .input("user", DatabaseExecutor.extract("login"))
                .sleep(3000)
                .submit()
                .input("password", DatabaseExecutor.extract("password"))
                .submit();
        new MainPage()
                .clickBoardsList()
                .clickOnBoardByName("KanbanTool");
        new BoardPage()
                .isCardInList("Done", "Карточка для изучения API")
                .clickOnCardByName("Карточка для изучения API")
                .isCheckboxSelected("Понять протокол HTTP")
                .isCheckboxSelected("Выучить методы запросов");
//                .windowMenuClick()
//                .chooseAndClickOnColor(Colors.GREEN)
//                .activateDateCheckbox()
//                .clickCloseButton()
//                .renameBoard("KanbanTool", "Только для образования");
    }

}
