package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Sticker extends Hooks {

    public void createSticker(String idCard, String stickerName, int top, int left, int zIndex) {
        Response response = given().spec(specification)
                .pathParam("id", idCard)
                .queryParams("image", stickerName, "top", top, "left", left, "zIndex", zIndex)
                .when()
                .post(EndPoints.CREATE_STICKER.getEndPoint());
        response.then().statusCode(200);
    }
}
