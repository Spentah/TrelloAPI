package api.utils;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtil {

    private static RequestSpecification specification;

    private RequestSpecUtil() {

    }

    public static RequestSpecification getSpecification() {
        return specification;
    }

    public static void setSpecification() {
        specification = new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri("https://api.trello.com/1/")
                .addQueryParam("key", DatabaseExecutor.executeValue("key"))
                .addQueryParam("token", DatabaseExecutor.executeValue("token"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
