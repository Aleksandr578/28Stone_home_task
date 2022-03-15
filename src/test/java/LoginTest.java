import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PropertiesReader.getProperties;

public class LoginTest {

    private static final String FILE_NAME = "drivers/chromedriver.exe";
    private static final String HOME_PAGE = "home.page";

    private static final String USER_NAME_ID = "user-name";
    private static final String PASSWORD_ID = "password";

    private static final String VALID_USER_NAME = "standard_user";
    private static final String LOCKED_USER_NAME = "locked_out_user";
    private static final String PROBLEM_USER_NAME = "problem_user";
    private static final String PERFORMANCE_GLITCH_USER_NAME = "performance_glitch_user";
    private static final String CAPS_LOCK_VALID_USER_NAME = "STANDARD_USER";

    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String INVALID_PASSWORD = "some_password";

    private static final String LOGIN_BUTTON_ID = "login-button";
    private static final String CLASS_NAME_PRODUCTS = "title";
    private static final String PRODUCTS_TITLE = "PRODUCTS";


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", FILE_NAME);
        driver = new ChromeDriver();
        driver.get(getProperties().getProperty(HOME_PAGE));
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void LoginStandardUserAndValidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(VALID_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        String titleName = driver.findElement(By.className(CLASS_NAME_PRODUCTS)).getText();

        assertThat("Products page is open", titleName.equals(PRODUCTS_TITLE));

    }

    @Test
    public void LockedOutUserAndValidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(LOCKED_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("User is locked", errorCodeWebElement.isDisplayed());
    }

    @Test
    public void LoginPerformanceGlitchUserAndValidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(PERFORMANCE_GLITCH_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        String titleName = driver.findElement(By.className(CLASS_NAME_PRODUCTS)).getText();

        assertThat("Products page is open", titleName.equals(PRODUCTS_TITLE));
    }

    @Test
    public void LoginProblemUserAndValidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(PROBLEM_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        String titleName = driver.findElement(By.className(CLASS_NAME_PRODUCTS)).getText();

        assertThat("Products page is open", titleName.equals(PRODUCTS_TITLE));
    }

    @Test
    public void LoginStandardUserAndInvalidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(VALID_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("Password is not correct", errorCodeWebElement.isDisplayed());
    }

    @Test
    public void LockedOutUserAndInvalidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(LOCKED_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("Password is not correct", errorCodeWebElement.isDisplayed());
    }

    @Test
    public void LoginPerformanceGlitchUserAndInvalidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(PERFORMANCE_GLITCH_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("Password is not correct", errorCodeWebElement.isDisplayed());
    }

    @Test
    public void LoginProblemUserAndInvalidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(PROBLEM_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("Password is not correct", errorCodeWebElement.isDisplayed());
    }

    @Test
    public void LoginStandardUserCapsLockAndValidPassword() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(CAPS_LOCK_VALID_USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("User is locked", errorCodeWebElement.isDisplayed());
    }
    @Test
    public void EmptyInputFields() {
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();

        WebElement errorCodeWebElement = driver.findElement(By.className("error-button"));

        assertThat("User is locked", errorCodeWebElement.isDisplayed());
    }
}
