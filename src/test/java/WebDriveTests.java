import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class WebDriveTests {
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
    void textInput() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().fullscreen();
        WebElement textInput = driver.findElement(By.cssSelector("#my-text-id"));
        Thread.sleep(3000);
        textInput.sendKeys("Ann");
        Thread.sleep(3000);
    }
    @Test
    void fileLoad() throws IOException, InterruptedException {

        String uploadFile = "src/test/resources/text.txt";

        String content = new String(Files.readAllBytes(Paths.get(uploadFile)));

        System.out.println("Содержимое файла: " + content);
        URL url = WebDriveTests.class.getClassLoader().getResource("text.txt");

        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
            System.out.println("Абсолютный путь к файлу: " + absolutePath);
        } else {
            System.out.println("Ресурс не найден.");
        }


        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().fullscreen();
        WebElement fileLoad = driver.findElement(By.cssSelector("input[type=file]"));
        fileLoad.sendKeys(absolutePath);
        Thread.sleep(5000);
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(5000);
    }
    @Test
    void dateInput() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().fullscreen();
        WebElement dateInput = driver.findElement(By.xpath("//input[@name='my-date']"));
        dateInput.click();
        dateInput.sendKeys("05/15/25");
        dateInput.click();
        Thread.sleep(3000);
    }
    @Test
    void checkInput() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().fullscreen();
        WebElement checkInput = driver.findElement(By.id("my-check-2"));
        checkInput.click();
        Thread.sleep(3000);
    }
    @Test
    void navigation() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        driver.manage().window().fullscreen();
        WebElement navigation2 = driver.findElement(By.xpath("//a[@href = 'navigation2.html']"));
        navigation2.click();
        WebElement navigation3 = driver.findElement(By.xpath("//a[@href = 'navigation3.html']"));
        navigation3.click();
        WebElement index = driver.findElement(By.xpath("//a[@href = 'index.html']"));
        index.click();
        Thread.sleep(3000);
    }
    @Test
    void dropDownTests() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        WebElement dropdownSelectMenu = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(dropdownSelectMenu)
                .perform();
        Thread.sleep(2000);
    }
    @Test
    void actionAPIDragAndDropTests() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Thread.sleep(2000);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("target"));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
        Thread.sleep(2000);
    }
}
