package frontendtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import frontendtests.frontend.methods.Methods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import frontendtests.frontend.pages.BookStorePage;
import frontendtests.frontend.pages.LoginPage;
import frontendtests.frontend.pages.ProfilePage;

public class BaseFrontEndTest {

    public WebDriver webDriver;
    public LoginPage loginPage;
    public BookStorePage storePage;
    public ProfilePage profile;
    public Methods methods;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
        storePage = new BookStorePage(webDriver);
        methods = new Methods(webDriver);
        profile = new ProfilePage(webDriver);
    }

    @AfterEach
    void teardown() {
        webDriver.quit();
    }

}
