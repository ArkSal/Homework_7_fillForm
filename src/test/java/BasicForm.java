import helpers.FileHelper;
import helpers.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicForm extends TestBase {

    @BeforeEach
    void setUp() {
        driver.get("https://seleniumui.moderntester.pl/form.php");
    }

    @Test
    @DisplayName("Testing success message")
    @Tag("regression")
    void fillForm() {
        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys("Arkadiusz");
        driver.findElement(By.cssSelector("#inputLastName3")).sendKeys("Mistrz");
        driver.findElement(By.cssSelector("#inputEmail3")).sendKeys("mistrz@gmail.com");
        List<WebElement> gender = driver.findElements(By.cssSelector("[name=gridRadiosSex]"));
        gender.get(new Random().nextInt(3)).click();
        driver.findElement(By.cssSelector("#inputAge3")).sendKeys("30");
        driver.findElement(By.cssSelector("#gridRadios" + RandomGenerator.randomFunction(7))).click();
        driver.findElement(By.cssSelector("#gridCheckAutomationTester")).click();
        List<WebElement> continents = driver.findElements(By.xpath("//*[@id='selectContinents']//option"));
        continents.get(RandomGenerator.randomFunction(2, continents.size() - 1)).click();
        driver.findElement(By.cssSelector("option[value='switch-commands']")).click();
        driver.findElement(By.cssSelector("option[value='wait-commands']")).click();
        driver.findElement(By.cssSelector("#chooseFile")).sendKeys(FileHelper.fileToLoadPath());
        driver.findElement(By.cssSelector(".btn-primary")).submit();
        String expectedMessage = driver.findElement(By.cssSelector("#validator-message")).getText();
        assertEquals("Form send with success", expectedMessage);
    }

    @Test
    @DisplayName("Testing downloaded files")
    @Tag("regression")
    void downloadFile() {
        int filesQuantityBeforeDownload = FileHelper.getFilesQuantityFromDownloadDirectory();
        driver.findElement(By.cssSelector(".btn-secondary")).click();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            int filesQuantityAfterDownload = FileHelper.getFilesQuantityFromDownloadDirectory();
            assertEquals(filesQuantityBeforeDownload, filesQuantityAfterDownload - 1);
        }
    }

    @Test
    @DisplayName("Testing download file name")
    @Tag("regression")
    void checkDownloadedFileName() {
        FileHelper.getFilesQuantityFromDownloadDirectory();
        assertTrue(FileHelper.checkIfFileWithSpecificNameExist("test-file-to-download"));
    }
}