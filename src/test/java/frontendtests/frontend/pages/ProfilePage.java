package frontendtests.frontend.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    private static final String PROFILE_URL = "https://demoqa.com/profile";
    public static final String ALERT_MESSAGE = "Book deleted.";

    @FindBy(xpath = "//div[@class='text-right button di']")
    WebElement deleteAllBooksFromCollectionButton;

    @FindBy(xpath = "//span[@id='delete-record-undefined']")
    WebElement bookToDelete;

    @FindBy(xpath = "//span[@id='delete-record-undefined']")
    List<WebElement> booksToDelete;

    @FindBy(id = "searchBox")
    WebElement searchBox;

    @FindBy(id = "closeSmallModal-ok")
    WebElement confirmDeletionButton;

    public ProfilePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void open() {
        driver.get(PROFILE_URL);
    }

    public void setSearchBookByName(String title) {
        searchBox.sendKeys(title);
    }

    public void deleteBook() {
        bookToDelete.click();
    }

    public void deleteAllBooksFromCollection() {
        bookToDelete.click();
    }

    public void confirmDeletion() {
        confirmDeletionButton.click();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void closeAlert() {
        driver.switchTo().alert().accept();
    }

    public int getCollectionSize() {
        return booksToDelete.size();
    }
}
