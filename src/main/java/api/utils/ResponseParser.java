package api.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseParser {

    private ResponseParser() {

    }

    public static String parse(Response response, String parameter) {
        return JsonPath.from(response.asString()).getString(parameter);
    }
}
