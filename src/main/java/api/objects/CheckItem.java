package api.objects;

import api.endpoints.EndPoints;
import hooks.Hooks;
import io.restassured.response.Response;
import api.utils.ResponseParser;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CheckItem extends Hooks {

    private Map<String, String> itemsId = new HashMap<>();

    private String id;

    public void createCheckItem(String idChecklist, String name) {
        Response response = given().spec(specification)
                .pathParam("id", idChecklist)
                .queryParam("name", name)
                .when()
                .post(EndPoints.CHECKITEM.getEndPoint());
        response.then().statusCode(200);

        itemsId.put(name, ResponseParser.parse(response, "id"));

//        id = ResponseParser.parse(response, "id");
    }

    public void updateCheckItem(String idCard, String idCheckItem, String state) {

        if (state.equalsIgnoreCase("complete") || state.equalsIgnoreCase("incomplete")) {
            Response response = given().spec(specification)
                    .pathParam("id", idCard)
                    .pathParam("idCheckItem", idCheckItem)
                    .queryParam("state", state)
                    .when()
                    .put(EndPoints.UPDATE_CHECKITEM.getEndPoint());
            response.then().statusCode(200);
        } else {
            Assert.fail("Введенные данные не подходят. Параметр \"state\" может быть только complete или incomplete");
            return;
        }
    }

    public String getId(String name) {
        return itemsId.get(name);
    }
}
