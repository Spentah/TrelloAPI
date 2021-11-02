package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import hooks.Hooks;
import io.restassured.response.Response;
import api.utils.ResponseParser;

import static io.restassured.RestAssured.given;

public class Checklist {

    private String id;

    public String getId() {
        return id;
    }

    public void createChecklist(String idCard) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idCard)
                .when()
                .post(EndPoints.CHECKLIST.getEndPoint());
        response.then().statusCode(200);

        id = ResponseParser.parse(response, "id");
    }


}
