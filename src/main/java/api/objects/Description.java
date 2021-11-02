package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Description {

    @Step("В карточке добавляем описание '{description}'")
    public void addDescription(String idCard, String description) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idCard)
                .queryParam("desc", description)
                .when()
                .put(EndPoints.UPDATE_CARD.getEndPoint());
        response.then().statusCode(200);
    }

}
