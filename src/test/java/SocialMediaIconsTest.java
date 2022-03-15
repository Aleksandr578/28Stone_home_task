import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PropertiesReader.getProperties;

public class SocialMediaIconsTest {

    private static final String FILE_NAME = "drivers/chromedriver.exe";
    private static final String HOME_PAGE = "home.page";
    private static final String USER_NAME_ID = "user-name";
    private static final String USER_NAME = "standard_user";
    private static final String PASSWORD_ID = "password";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOGIN_BUTTON_ID = "login-button";

    private static final String TWITTER_PAGE = "https://twitter.com/saucelabs";
    private static final String LINKEDIN_PAGE = "www.linkedin";
    private static final String FACEBOOK_PAGE = "https://www.facebook.com/saucelabs";
    private static final String ABOUT_COMPANY_PAGE = "https://saucelabs.com/";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", FILE_NAME);
        driver = new ChromeDriver();
        driver.get(getProperties().getProperty(HOME_PAGE));

        login();
    }

    @Test
    public void FollowTheFacebookSocialNetworkPageTest() {
        driver.findElement(By.className("social_facebook")).click();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        assertThat("Correct window is open", driver.getCurrentUrl().equals(FACEBOOK_PAGE));
    }

    @Test
    public void FollowTheLinkedinSocialNetworkPageTest() {
        driver.findElement(By.className("social_linkedin")).click();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        assertThat("Correct window is open", driver.getCurrentUrl().contains(LINKEDIN_PAGE));
    }

    @Test
    public void FollowTheTwitterSocialNetworkPageTest() {
        driver.findElement(By.className("social_twitter")).click();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        assertThat("Correct window is open", driver.getCurrentUrl().equals(TWITTER_PAGE));
    }


    @Test
    public void InformationAboutCompanyTest() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("about_sidebar_link")).click();

        assertThat("Correct window is open", driver.getCurrentUrl().equals(ABOUT_COMPANY_PAGE));
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    private void login() {
        driver.findElement(By.id(USER_NAME_ID)).sendKeys(USER_NAME);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
    }
}
