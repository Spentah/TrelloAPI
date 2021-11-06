package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import api.utils.ResponseParser;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Board {

    private static HashMap<String, String> idKeeper = new HashMap<>();

    @Step("Создаем доску с названием '{name}'")
    public void createBoard(String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification()).log().all()
                .queryParam("name", name)
                .when()
                .post(EndPoints.BOARD.getEndPoint());
        response.then().statusCode(200);
        idKeeper.put(name, ResponseParser.parse(response, "id"));
    }

    @Step("Сворачиваем лавочку")
    public static void deleteBoard(String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idKeeper.get(name))
                .when()
                .delete(EndPoints.DELETE_BOARD.getEndPoint());
        response.then().statusCode(200);
        idKeeper.clear();
    }

    public static String getIdByName(String name) {
        return idKeeper.get(name);
    }

    public static void updateName(String newName, String oldName) {
        idKeeper.put(newName, idKeeper.get(oldName));
        idKeeper.remove(oldName);
    }
}
