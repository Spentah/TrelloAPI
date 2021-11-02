package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Sticker {

    @Step("Добавляем стикер 'Палец вверх'")
    public void createSticker(String idCard, String stickerName, int top, int left, int zIndex) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idCard)
                .queryParams("image", stickerName, "top", top, "left", left, "zIndex", zIndex)
                .when()
                .post(EndPoints.CREATE_STICKER.getEndPoint());
        response.then().statusCode(200);
    }
}
