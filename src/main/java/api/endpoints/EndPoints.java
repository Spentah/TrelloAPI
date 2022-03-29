package api.endpoints;

public class EndPoints {

    private EndPoints() {
    }

    public static final String CARD = "cards";
    public static final String UPDATE_CARD = "cards/{id}";
    public static final String BOARD = "boards/";
    public static final String DELETE_BOARD = "boards/{id}";
    public static final String CHECKLIST = "cards/{id}/checklists";
    public static final String CHECK_ITEM = "checklists/{id}/checkItems";
    public static final String ATTACHMENT = "cards/{id}/attachments";
    public static final String UPDATE_CHECKITEM = "cards/{id}/checkItem/{idCheckItem}";
    public static final String ARCHIVE_LIST = "lists/{id}/closed";
    public static final String MOVE_CARD = "lists/{id}/moveAllCards";
    public static final String CREATE_STICKER = "cards/{id}/stickers";
    public static final String LIST = "boards/{id}/lists";

}
