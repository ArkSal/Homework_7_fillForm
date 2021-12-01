import helpers.FileHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestBase {
    WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("WebDriver initialized");
        FileHelper.createDirectory();
        logger.info("Download files directory created");
    }

    @BeforeEach
    void setup() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", FileHelper.getDownloadFile().getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        logger.info("Driver initialized with additional options");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Driver quit done properly");
    }
}
