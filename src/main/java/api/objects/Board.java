package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import api.utils.ResponseParser;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Board {

    private String id;

    @Step("Создаем доску с названием '{name}'")
    public void createBoard(String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification()).log().all()
                .queryParam("name", name)
                .when()
                .post(EndPoints.BOARD.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    @Step("Удаляем доску с названием '{name}'")
    public void deleteBoard(String idBoard) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idBoard)
                .when()
                .delete(EndPoints.DELETE_BOARD.getEndPoint());
        response.then().statusCode(200);
    }

    public String getId() {
        return id;
    }
}
