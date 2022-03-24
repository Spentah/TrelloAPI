import api.TrelloApi;
import api.objects.*;

import api.utils.DatabaseExecutor;
import hooks.Hooks;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.pages.BoardPage;
import ui.Colors;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TestTrelloApi extends Hooks {

    @Test(groups = {"api"})
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
//                .archiveList()
                .updateCheckItem("Выучить методы запросов")
                .createSticker();
    }

//    @Parameters("environment")
    @Test(groups = {"testing"})
    public void testing() {
//        System.out.println("Current environment - " + environment);
        System.out.println("Current stand name - " + System.getProperty("stand"));
        System.out.println("Current value - " + System.getProperty("url"));
//        System.out.println("Current value of env - " + System.getenv("my_env"));
    }

}
