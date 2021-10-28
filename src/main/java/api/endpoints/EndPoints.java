package api.endpoints;

public enum EndPoints {

    CARD ("/1/cards"),
    UPDATE_CARD ("/1/cards/{id}"),
    BOARD ("/1/boards/"),
    DELETE_BOARD("/1/boards/{id}"),
    CHECKLIST ("/1/cards/{id}/checklists"),
    CHECKITEM ("/1/checklists/{id}/checkItems"),
    ATTACHMENT ("/1/cards/{id}/attachments"),
    UPDATE_CHECKITEM ("/1/cards/{id}/checkItem/{idCheckItem}"),
    MOVE_CARD ("/1/lists/{id}/moveAllCards"),
    ARCHIVE_LIST ("/1/lists/{id}/closed"),
    CREATE_STICKER ("/1/cards/{id}/stickers"),
    LIST ("/1/boards/{id}/lists");

    private String endPoint;

    EndPoints(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
