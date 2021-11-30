import helpers.FileHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        FileHelper.createDirectory();
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
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }
}
