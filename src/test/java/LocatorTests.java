import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorTests {
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
    void baseLocatorsTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().fullscreen();
        WebElement textInputById = driver.findElement(By.id("my-text-id"));
        textInputById.sendKeys("Hello");
        Thread.sleep(1000);
        WebElement textInputPassword = driver.findElement(By.name("my-password"));
        textInputPassword.sendKeys("123");
        Thread.sleep(1000);
        WebElement textInputTextarea= driver.findElement(By.name("my-textarea"));
        textInputTextarea.sendKeys("Hard");
        Thread.sleep(1000);
    }


    @Test
    void cssSelectorsTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textInputById = driver.findElement(By.cssSelector("#my-text-id"));
        textInputById.sendKeys("textInputById");
        Thread.sleep(1000);
    }

    @Test
    void xpathSelectorsTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement byTag = driver.findElement(By.xpath("//input"));
        byTag.sendKeys("byTag");
        Thread.sleep(1000);
    }
}