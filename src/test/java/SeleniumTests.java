import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {
    WebDriver driver;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void openSiteTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }
    }