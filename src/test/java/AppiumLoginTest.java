import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AppiumLoginTest {

    private static final String SERVER = "http://127.0.0.1:4723/";
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    private AndroidDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setPlatformVersion("15")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .noReset()
                .withBrowserName("Chrome");

        driver = new AndroidDriver(new URL(SERVER), options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void successfulLoginTest() {
        driver.get(BASE_URL);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("display-6")));

        WebElement loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("username")));

        WebElement passwordInput = driver.findElement(AppiumBy.id("password"));
        WebElement submitButton = driver.findElement(AppiumBy.xpath("//button[@type='submit']"));

        loginInput.sendKeys("user");
        passwordInput.sendKeys("user");

        String textBeforeClick = subTitle.getText();
        submitButton.click();


        assertThat(textBeforeClick).isEqualTo("Login form");

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("success")));
        assertThat(successMessage.isDisplayed()).isTrue();
    }
}
