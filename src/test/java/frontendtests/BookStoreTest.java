package frontendtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import frontendtests.frontend.pages.BookStorePage;
import frontendtests.frontend.pages.ProfilePage;

class BookStoreTest extends BaseFrontEndTest {

    @Test
    void addBookToCollectionTest() throws InterruptedException {
        addBookToCollection("Git Pocket Guide");
        assertEquals(BookStorePage.ALERT_MESSAGE, storePage.getAlertMessageText(), "Alert message is correct");
        storePage.closeAlert();
        deleteAllBooks();
    }

    @Test
    void deleteBookToCollectionTest() throws InterruptedException {
        addBookToCollection("Learning JavaScript Design Patterns");
        methods.waitTime();
        storePage.closeAlert();
        int counter = profile.getCollectionSize();
        profile.open();
        profile.setSearchBookByName("Learning JavaScript Design Patterns");
        profile.deleteBook();
        profile.confirmDeletion();
        methods.waitTime();
        assertEquals(ProfilePage.ALERT_MESSAGE, profile.getAlertText(), "Alert message is correct");
        profile.closeAlert();
        assertEquals(counter--, profile.getCollectionSize(), "Collection size is correct");
    }

    void addBookToCollection(String bookName) throws InterruptedException {
        storePage.open();
        storePage.loginBtnClick();
        loginPage.enterLogin(BookStorePage.USERNAME);
        loginPage.enterPassword(BookStorePage.PASSWORD);
        loginPage.loginBtnClick();
        storePage.clickOnBookByTitle(bookName);
        storePage.addBookToCollection();
        methods.waitTime();
    }

    void deleteAllBooks() throws InterruptedException {
        profile.open();
        profile.deleteAllBooksFromCollection();
        profile.confirmDeletion();
        methods.waitTime();
        profile.closeAlert();
    }
}
