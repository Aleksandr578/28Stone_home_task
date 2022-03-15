import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PropertiesReader.getProperties;

public class AddItemTest {

    private static final String FILE_NAME = "drivers/chromedriver.exe";
    private static final String HOME_PAGE = "home.page";

    private static final String USER_NAME_ID = "user-name";
    private static final String USER_NAME = "standard_user";
    private static final String PASSWORD_ID = "password";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOGIN_BUTTON_ID = "login-button";

    private static final String ADD_BACKPACK_ID = "add-to-cart-sauce-labs-backpack";
    private static final String ONESIE_ID = "add-to-cart-sauce-labs-onesie";

    private static final String EXPECTED_BACKPACK_NAME = "Sauce Labs Backpack";
    private static final String EXPECTED_ONESIE_NAME = "Sauce Labs Onesie";

    private static final String SHOPPING_CART_CLASS_NAME = "shopping_cart_link";
    private static final String INVENTORY_ITEM_CLASS_NAME = "inventory_item_name";

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
    public void CorrectItemIsInTheCard() {
        driver.findElement(By.id(ADD_BACKPACK_ID)).click();
        driver.findElement(By.className(SHOPPING_CART_CLASS_NAME)).click();
        String itemName = driver.findElement(By.className(INVENTORY_ITEM_CLASS_NAME)).getText();

        assertThat("Item is correct", itemName.equals(EXPECTED_BACKPACK_NAME));
    }

    @Test
    public void CorrectTwoDifferentItemsAreInTheCard() {
        driver.findElement(By.id(ADD_BACKPACK_ID)).click();
        driver.findElement(By.id(ONESIE_ID)).click();

        driver.findElement(By.className(SHOPPING_CART_CLASS_NAME)).click();

        List<String> itemNames = driver.findElements(By.className(INVENTORY_ITEM_CLASS_NAME))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());

        assertThat("Backpack is in the cart", itemNames.contains(EXPECTED_BACKPACK_NAME));
        assertThat("Onesie is in the cart", itemNames.contains(EXPECTED_ONESIE_NAME));
    }

    private void login() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
    }
}
