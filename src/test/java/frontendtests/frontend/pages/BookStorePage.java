package frontendtests.frontend.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage extends BasePage {

    private static final String BOOK_STORE_URL = "https://demoqa.com/books";
    public static final String USERNAME = "Test712";
    public static final String PASSWORD = "Test712!";
    public static final String ALERT_MESSAGE = "Book added to your collection.";

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(xpath = "//span[@class='mr-2']")
    List<WebElement> books;

    @FindBy(xpath = "//div[@class='text-right fullButton']")
    WebElement addBookButton;

    public BookStorePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void open() {
        driver.get(BOOK_STORE_URL);
    }

    public void loginBtnClick() {
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public void clickOnBook() {
        books.stream().findFirst().get().click();
    }

    public void addBookToCollection() {
        addBookButton.click();
    }

    public void clickOnBookByTitle(String title) {
        driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
    }

    public String getAlertMessageText() {
        return driver.switchTo().alert().getText();
    }

    public void closeAlert() {
        driver.switchTo().alert().accept();
    }
}
