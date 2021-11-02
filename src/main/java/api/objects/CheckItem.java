package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import api.utils.ResponseParser;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CheckItem {

    private Map<String, String> itemsId = new HashMap<>();

    private String id;

    @Step("Создаем чекбокс с названием '{name}'")
    public void createCheckItem(String idChecklist, String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idChecklist)
                .queryParam("name", name)
                .when()
                .post(EndPoints.CHECKITEM.getEndPoint());
        response.then().statusCode(200);

        itemsId.put(name, ResponseParser.parse(response, "id"));
    }

    @Step("Активируем чекбокс '{idCheckItem}'")
    public void updateCheckItem(String idCard, String idCheckItem, String state) {
        if (state.equalsIgnoreCase("complete") || state.equalsIgnoreCase("incomplete")) {
            Response response = given().spec(RequestSpecUtil.getSpecification())
                    .pathParam("id", idCard)
                    .pathParam("idCheckItem", idCheckItem)
                    .queryParam("state", state)
                    .when()
                    .put(EndPoints.UPDATE_CHECKITEM.getEndPoint());
            response.then().statusCode(200);
        } else {
            Assert.fail("Введенные данные не подходят. Параметр \"state\" может быть равен только complete или incomplete");
            return;
        }
    }

    public String getId(String name) {
        return itemsId.get(name);
    }
}