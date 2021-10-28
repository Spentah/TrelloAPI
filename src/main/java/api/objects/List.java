package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;
import api.utils.ResponseParser;

import static io.restassured.RestAssured.given;

public class List extends Hooks {

    private String id;

    public void createList(String boardId, String listName) {
        Response response = given().spec(specification)
                .pathParam("id", boardId)
                .queryParam("name", listName)
                .post(EndPoints.LIST.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    public void moveList(String idListOld, String idBoard, String idListNew) {
        Response response = given().spec(specification)
                .pathParam("id", idListOld)
                .queryParams("idBoard", idBoard, "idList", idListNew)
                .when()
                .post(EndPoints.MOVE_CARD.getEndPoint());
        response.then().statusCode(200);
    }

    public void archiveList(String idList, boolean value) {
        Response response = given().spec(specification)
                .pathParam("id", idList)
                .queryParams("value", value)
                .when()
                .put(EndPoints.ARCHIVE_LIST.getEndPoint());
        response.then().statusCode(200);
    }

    public String getId() {
        return id;
    }
}
