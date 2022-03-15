import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PropertiesReader.getProperties;

public class RemoveItemTest {

    private static final String FILE_NAME = "drivers/chromedriver.exe";
    private static final String HOME_PAGE = "home.page";

    private static final String USER_NAME_ID = "user-name";
    private static final String USER_NAME = "standard_user";
    private static final String PASSWORD_ID = "password";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOGIN_BUTTON_ID = "login-button";

    private static final String ADD_BACKPACK_ID = "add-to-cart-sauce-labs-backpack";
    private static final String REMOVE_BACKPACK_ID = "remove-sauce-labs-backpack";
    private static final String ADD_TO_CART_BUTTON_NAME = "ADD TO CART";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", FILE_NAME);
        driver = new ChromeDriver();
        driver.get(getProperties().getProperty(HOME_PAGE));

        login();
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void ItemIsRemovedFromCart() {
        driver.findElement(By.id(ADD_BACKPACK_ID)).click();
        driver.findElement(By.id(REMOVE_BACKPACK_ID)).click();

        String buttonName = driver.findElement(By.id(ADD_BACKPACK_ID)).getText();

        assertThat("Item is removed", buttonName.equals(ADD_TO_CART_BUTTON_NAME));
    }

    private void login() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
    }
}
