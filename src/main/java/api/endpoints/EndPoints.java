package api.endpoints;

public enum EndPoints {

    CARD ("cards"),
    UPDATE_CARD ("cards/{id}"),
    BOARD ("boards/"),
    DELETE_BOARD("boards/{id}"),
    CHECKLIST ("cards/{id}/checklists"),
    CHECKITEM ("checklists/{id}/checkItems"),
    ATTACHMENT ("cards/{id}/attachments"),
    UPDATE_CHECKITEM ("cards/{id}/checkItem/{idCheckItem}"),
    MOVE_CARD ("lists/{id}/moveAllCards"),
    ARCHIVE_LIST ("lists/{id}/closed"),
    CREATE_STICKER ("cards/{id}/stickers"),
    LIST ("boards/{id}/lists");

    private final String endPoint;

    EndPoints(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
