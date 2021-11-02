package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static io.restassured.RestAssured.given;

public class Due {

    @Step("В карточке добавляем ставим время выполнения завтрашний день")
    public void createDue(String idCard) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);

        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idCard)
                .queryParam("due", calendar.getTime())
                .when()
                .put(EndPoints.UPDATE_CARD.getEndPoint());
        response.then().statusCode(200);
    }
}
