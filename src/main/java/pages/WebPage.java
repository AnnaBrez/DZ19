package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebPage {
    WebDriver driver;

    private static final String VALID_TEXT = "ann";
    private static final String VALID_PASSWORD = "one";
    private static final String VALID_AREA = "hello";

    By textInput = By.name("my-text");
    By passwordInput = By.name("my-password");
    By areaInput = By.name("my-textarea");
    By submitButton = By.xpath("//button[@type='submit']");

    public WebPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    }


    public void successfulSignIn() {
        driver.findElement(textInput).sendKeys(VALID_TEXT);
        driver.findElement(passwordInput).sendKeys(VALID_PASSWORD);
        driver.findElement(areaInput).sendKeys(VALID_AREA);
        driver.findElement(submitButton).click();
    }

    public void SignIn(String text, String password, String area) {
        driver.findElement(textInput).sendKeys(text);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(areaInput).sendKeys(area);
        driver.findElement(submitButton).click();
    }

    public boolean successMessageIsPresent() {
        return driver.findElement(By.id("success")).isDisplayed();
    }
}