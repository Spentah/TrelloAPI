package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import hooks.Hooks;
import io.restassured.response.Response;
import api.utils.ResponseParser;

import static io.restassured.RestAssured.given;

public class Card {

    private String id;

    public void createCard(String idList, String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .queryParam("idList", idList)
                .queryParam("name", name)
                .when()
                .post(EndPoints.CARD.getEndPoint());
//                .post("/1/cards");
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }

    public String getId() {
        return id;
    }
}
