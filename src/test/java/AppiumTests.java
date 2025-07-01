import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


import static org.assertj.core.api.Assertions.assertThat;

public class AppiumTests {
    private static final String APP = "https://github.com/appium-pro/TheApp/releases/download/v1.12.0/TheApp.apk";
    private static final String SERVER = "http://127.0.0.1:4723/";
    private AndroidDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        DesiredCapabilities ds = new DesiredCapabilities();
        ds.setCapability("platformName", "Android");
        ds.setCapability("platformVersion", "15");
        ds.setCapability("deviceName", "emulator-5554");
        ds.setCapability("app", APP);
        ds.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL(SERVER), ds);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    void loginTest() throws InterruptedException {
        String user = "alice";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        login.click();
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("username")));
        username.sendKeys(user);
        driver.findElement(AppiumBy.accessibilityId("password")).sendKeys("mypassword");
        driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();
        Thread.sleep(3000);
        String source = driver.getPageSource();
        System.out.println("SourceCode:" + source);
        WebElement loginMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));
        assertThat(loginMessage.getText()).contains(user);
    }
}