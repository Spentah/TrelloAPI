package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import api.utils.ResponseParser;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class List {

    private String id;

    @Step("Создаем колонку с названием '{listName}'")
    public void createList(String boardId, String listName) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", boardId)
                .queryParam("name", listName)
                .post(EndPoints.LIST);
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    @Step("Перносим карточки из колонки 'Backlog' в колокну 'Done'")
    public void moveList(String idListOld, String idBoard, String idListNew) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idListOld)
                .queryParams("idBoard", idBoard, "idList", idListNew)
                .when()
                .post(EndPoints.MOVE_CARD);
        response.then().statusCode(200);
    }

    @Step("Архивируем колонку 'Backlog'")
    public void archiveList(String idList, boolean value) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idList)
                .queryParams("value", value)
                .when()
                .put(EndPoints.ARCHIVE_LIST);
        response.then().statusCode(200);
    }

    public String getId() {
        return id;
    }
}
