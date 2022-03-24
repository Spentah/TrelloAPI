package hooks;

import api.utils.RequestSpecUtil;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.reporters.jq.Main;
import api.utils.DatabaseExecutor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;


public class Hooks {

    private static Logger LOGGER;
    private static final String SELENOID_HUB = "http://localhost:4444/wd/hub/";

    @BeforeTest(alwaysRun = true)
    public void setUp() throws IOException {
        LOGGER = Logger.getLogger(Main.class.getName());
//        setUpSelenoid();
        DatabaseExecutor.getConnect();
        RequestSpecUtil.setSpecification();
        Configuration.startMaximized = true;
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("users.properties"));
        Configuration.timeout = 10000;
//        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    public static void setUpSelenoid() {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setBrowserName("chrome");
        dc.setCapability("browserVersion", "98.0");
        dc.setCapability("enableVNC", true);
        try {
            WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(SELENOID_HUB), dc));
            WebDriverRunner.getWebDriver().manage().window().setPosition(new Point(0, 0));
            WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1920, 1080));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
//        Board.deleteBoard("KanbanTool");
        DatabaseExecutor.closeConnect();
    }
}
