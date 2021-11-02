package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import hooks.Hooks;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Description {

    public void addDescription(String idCard, String description) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idCard)
                .queryParam("desc", description)
                .when()
                .put(EndPoints.UPDATE_CARD.getEndPoint());
//                .put("/1/cards/{id}");
        response.then().statusCode(200);
    }

}
