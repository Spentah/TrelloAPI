package api.objects;

import api.endpoints.EndPoints;
import api.utils.RequestSpecUtil;
import api.utils.ResponseParser;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CheckItem {

    private final Map<String, String> itemsId = new HashMap<>();

    @Step("Создаем чекбокс с названием '{name}'")
    public void createCheckItem(String idChecklist, String name) {
        Response response = given().spec(RequestSpecUtil.getSpecification())
                .pathParam("id", idChecklist)
                .queryParam("name", name)
                .when()
                .post(EndPoints.CHECK_ITEM);
        response.then().statusCode(200);

        itemsId.put(name, ResponseParser.parse(response, "id"));
    }

    @Step("Активируем чекбокс '{name}'")
    public void updateCheckItem(String idCard, String name, String state) {
        if (state.equalsIgnoreCase("complete") || state.equalsIgnoreCase("incomplete")) {
            Response response = given().spec(RequestSpecUtil.getSpecification())
                    .pathParam("id", idCard)
                    .pathParam("idCheckItem", getId(name))
                    .queryParam("state", state)
                    .when()
                    .put(EndPoints.UPDATE_CHECKITEM);
            response.then().statusCode(200);
        } else {
            Assert.fail("Введенные данные не подходят. Параметр \"state\" может быть равен только complete или incomplete");
        }
    }

    public String getId(String name) {
        return itemsId.get(name);
    }
}
