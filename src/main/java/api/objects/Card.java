package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import api.utils.ResponseParser;

import static io.restassured.RestAssured.given;

public class Card {

    private String id;

    @Step("Создаем карточку с названием '{name}'")
    public void createCard(String idList, String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .queryParam("idList", idList)
                .queryParam("name", name)
                .when()
                .post(EndPoints.CARD.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    public String getId() {
        return id;
    }
}
