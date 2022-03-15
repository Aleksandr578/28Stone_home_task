import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PropertiesReader.getProperties;

public class CheckoutTest {

    private static final String FILE_NAME = "drivers/chromedriver.exe";
    private static final String HOME_PAGE = "home.page";

    private static final String USER_NAME_ID = "user-name";
    private static final String USER_NAME = "standard_user";
    private static final String PASSWORD_ID = "password";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOGIN_BUTTON_ID = "login-button";

    private static final String CHECK_OUT_BUTTON_ID = "checkout";
    private static final String CONTINUE_BUTTON_ID = "continue";
    private static final String FINISH_BUTTON_ID = "finish";

    private static final String FIRST_NAME_ID = "first-name";
    private static final String LAST_NAME_ID = "last-name";
    private static final String POSTAL_CODE_ID = "postal-code";

    private static final String FIRST_NAME = "Bob";
    private static final String LAST_NAME = "Naked";
    private static final String POSTAL_CODE = "LV1234";

    private static final String TITLE_ID = "title";

    private static final String ADD_T_SHIRT_ID = "add-to-cart-sauce-labs-bolt-t-shirt";

    private static final String T_SHIRT_ITEM_NAME = "Sauce Labs Bolt T-Shirt";

    private static final String SHOPPING_CART_CLASS_NAME = "shopping_cart_link";
    private static final String INVENTORY_ITEM_CLASS_NAME = "inventory_item_name";
    private static final String TOTAL_SUM_CLASS_NAME = "summary_total_label";
    private static final String CHECKOUT_COMPLETED = "CHECKOUT: COMPLETE!";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", FILE_NAME);
        driver = new ChromeDriver();
        driver.get(getProperties().getProperty(HOME_PAGE));

        login();
    }

    @Test
    public void tShirtWillBeDeliveredByPony() {
        driver.findElement(By.id(ADD_T_SHIRT_ID)).click();
        driver.findElement(By.className(SHOPPING_CART_CLASS_NAME)).click();
        driver.findElement(By.id(CHECK_OUT_BUTTON_ID)).click();
        driver.findElement(By.id(FIRST_NAME_ID)).sendKeys(FIRST_NAME);
        driver.findElement(By.id(LAST_NAME_ID)).sendKeys(LAST_NAME);
        driver.findElement(By.id(POSTAL_CODE_ID)).sendKeys(POSTAL_CODE);
        driver.findElement(By.id(CONTINUE_BUTTON_ID)).click();

        String itemName = driver.findElement(By.className(INVENTORY_ITEM_CLASS_NAME)).getText();
        assertThat("Item is correct", itemName.equals(T_SHIRT_ITEM_NAME));

        String totalSum = driver.findElement(By.className(TOTAL_SUM_CLASS_NAME)).getText();
        assertThat("Total sum is correct", totalSum.contains("17.27"));

        driver.findElement(By.id(FINISH_BUTTON_ID)).click();
        String title = driver.findElement(By.className(TITLE_ID)).getText();
        assertThat("Checkout completed", title.equals(CHECKOUT_COMPLETED));

    }

    private void login() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
