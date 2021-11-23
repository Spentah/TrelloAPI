package hooks;

import api.utils.RequestSpecUtil;
import com.codeborne.selenide.Configuration;
import api.objects.Board;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.jq.Main;
import api.utils.DatabaseExecutor;

import java.io.IOException;
import java.util.logging.Logger;


public class Hooks {

    protected static Logger LOGGER;

    @BeforeTest
    public void setUp() throws IOException {
        LOGGER = Logger.getLogger(Main.class.getName());
        DatabaseExecutor.getConnect();
        RequestSpecUtil.setSpecification();
        Configuration.startMaximized = true;
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("users.properties"));
        Configuration.timeout = 10000;
    }

    @AfterTest
    public void tearDown() {
        Board.deleteBoard("KanbanTool");
        DatabaseExecutor.closeConnect();
    }
}
