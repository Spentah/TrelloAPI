package api;

import api.objects.*;

import java.util.HashMap;
import java.util.Map;

public class TrelloApi {
    private Board board = new Board();
    private List list = new List();
    private Card card = new Card();
    private Attachment attachment = new Attachment();
    private Description description = new Description();
    private Due due = new Due();
    private Checklist checklist = new Checklist();
    private CheckItem checkItem = new CheckItem();
    private List newList = new List();
    private Sticker sticker = new Sticker();
    private Map<String, String> listIds = new HashMap<>();

    public TrelloApi createBoard(String name) {
        board.createBoard(name);
        return this;
    }
    public TrelloApi createList(String name) {
        list.createList(Board.getIdByName("KanbanTool"), name);
        listIds.put(name, list.getId());
        return this;
    }

    public TrelloApi createCard(String name) {
        card.createCard(list.getId(), name);
        return this;
    }

    public TrelloApi createAttachment(String url) {
        attachment.createAttachment(card.getId(), url);
        return this;
    }

    public TrelloApi createDescription(String name) {
        description.addDescription(card.getId(), name);
        return this;
    }

    public TrelloApi createDue() {
        due.createDue(card.getId());
        return this;
    }

    public TrelloApi createChecklist() {
        checklist.createChecklist(card.getId());
        return this;
    }

    public TrelloApi createCheckItem(String name) {
        checkItem.createCheckItem(checklist.getId(), name);
        return this;
    }

    public TrelloApi updateCheckItem(String checkItemName) {
        checkItem.updateCheckItem(card.getId(), checkItemName, "complete");
        return this;
    }

    public TrelloApi moveList(String oldName, String newName) {
        list.moveList(listIds.get(oldName), Board.getIdByName("KanbanTool"), listIds.get(newName));
        return this;
    }

    public TrelloApi archiveList() {
        list.archiveList(list.getId(), true);
        return this;
    }

    public TrelloApi createSticker() {
        sticker.createSticker(card.getId(), "thumbsup", 50, 50, 0);
        return this;
    }
}
