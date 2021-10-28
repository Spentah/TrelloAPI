package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;
import api.utils.ResponseParser;

import static io.restassured.RestAssured.given;

public class Checklist extends Hooks {

    private String id;

    public String getId() {
        return id;
    }

    public void createChecklist(String idCard) {
        Response response = given().spec(specification)
                .pathParam("id", idCard)
                .when()
                .post(EndPoints.CHECKLIST.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }


}
