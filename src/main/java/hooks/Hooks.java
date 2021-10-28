package hooks;

import com.codeborne.selenide.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import api.objects.Board;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.jq.Main;
import api.utils.DatabaseExecutor;

import java.io.IOException;
import java.util.logging.Logger;


public class Hooks {

    protected static RequestSpecification specification;
    protected static RequestSpecification spec1;
    protected static Logger LOGGER;
    protected static Board board = new Board();

    @BeforeTest
    public void setUp() throws IOException {
        LOGGER = Logger.getLogger(Main.class.getName());
        DatabaseExecutor.getConnect();

        specification = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .addQueryParam("key", DatabaseExecutor.executeValue("key"))
                .addQueryParam("token", DatabaseExecutor.executeValue("token"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        Configuration.startMaximized = true;
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("users.properties"));
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        Configuration.timeout = 10000;

    }

    @AfterTest
    public void tearDown() {
        DatabaseExecutor.closeConnect();
//        board.deleteBoard(board.getId());
    }
}
