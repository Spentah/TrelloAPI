package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static io.restassured.RestAssured.given;

public class Due extends Hooks {

    public void createDue(String idCard) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);

        Response response = given().spec(specification)
                .pathParam("id", idCard)
                .queryParam("due", calendar.getTime())
                .when()
                .put(EndPoints.UPDATE_CARD.getEndPoint());
//                .put("/1/cards/{id}");
        response.then().statusCode(200);
    }
}
