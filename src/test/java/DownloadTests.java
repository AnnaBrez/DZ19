import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AllureSteps;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Download")
class DownloadTests {
    WebDriver driver;
    AllureSteps allureSteps = new AllureSteps();

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Check screenshot attachment")
    void testDownloadHttpClient() throws IOException {
        // Папка для загрузок
        File downloadsDir = new File("target/downloads");
        downloadsDir.mkdirs();

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        // Скачиваем PNG
        WebElement pngLink = driver.findElement(By.xpath("//a[@download='webdrivermanager.png']"));
        File pngFile = new File(downloadsDir, "webdrivermanager.png");
        allureSteps.download(pngLink.getAttribute("href"), pngFile);
        await().atMost(5, SECONDS).until(pngFile::exists);
        assertThat(pngFile).exists();

        // Скачиваем PDF
        WebElement pdfLink = driver.findElement(By.xpath("//a[@download='webdrivermanager.pdf']"));
        File pdfFile = new File(downloadsDir, "webdrivermanager.pdf");
        allureSteps.download(pdfLink.getAttribute("href"), pdfFile);
        await().atMost(5, SECONDS).until(pdfFile::exists);
        assertThat(pdfFile).exists();
        allureSteps.captureScreenshot(driver);
        allureSteps.captureScreenshotSpoiler(driver);
    }
}
