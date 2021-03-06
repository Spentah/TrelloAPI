import api.TrelloApi;
import hooks.Hooks;
import org.testng.annotations.Test;

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
                .moveList("Backlog", "Done")
                .updateCheckItem("Выучить методы запросов")
                .createSticker();
    }

    @Test(groups = {"testing"})
    public void testing() {
        System.out.println("Current stand name - " + System.getProperty("stand"));
        System.out.println("Current url - " + System.getProperty("url"));
    }

}
