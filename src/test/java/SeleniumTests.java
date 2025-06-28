import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AllureSteps;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    WebDriver driver;
    AllureSteps allureSteps = new AllureSteps();

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    @DisplayName("Check screenshot attachment")
    void openSiteTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        allureSteps.captureScreenshot(driver);
        allureSteps.captureScreenshotSpoiler(driver);
    }
    }