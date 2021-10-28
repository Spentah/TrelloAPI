package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Attachment extends Hooks {

    public void createAttachment(String cardId, String url) {
        Response response = given().spec(specification)
                .pathParam("id", cardId)
                .queryParam("url", url)
                .when()
                .post(EndPoints.ATTACHMENT.getEndPoint());
        response.then().statusCode(200);
    }
}
