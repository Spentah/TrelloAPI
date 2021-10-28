package api.objects;

import api.endpoints.EndPoints;
import api.utils.ResponseParser;
import hooks.Hooks;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Board extends Hooks {

    private String id;

    public void createBoard(String name) {
        Response response = given().spec(specification).log().all()
                .queryParam("name", name)
                .when()
                .post(EndPoints.BOARD.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    public void deleteBoard(String idBoard) {
        Response response = given().spec(specification)
                .pathParam("id", idBoard)
                .when()
                .delete(EndPoints.DELETE_BOARD.getEndPoint());
        response.then().statusCode(200);
    }

    public String getId() {
        return id;
    }
}
