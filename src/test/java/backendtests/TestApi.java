package backendtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import backendtests.dto.AddListOffBooks;
import backendtests.dto.AllBooksModal;
import backendtests.dto.CollectionsOfIsbn;
import backendtests.dto.GetUserResult;
import backendtests.dto.LoginViewModel;

import backendtests.dto.NewCollectionOfIsbn;
import backendtests.dto.RegisterViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class TestApi extends BaseBackEndTest {

    private static final String USERNAME = "BestUser";
    private static final String PASSWORD = "BestUser1!";
    List<CollectionsOfIsbn> collections = new ArrayList<>();

    @Test
    void addBookApiTest() {
        String userId = postUser(prepareBodyForUser()).getUserId();
        String token = generateToken(prepareBodyForUserToken()).getToken();
        AllBooksModal books = getBooks();
        collections.add(prepareCollection(books.getBooks().get(0).getIsbn()));
        NewCollectionOfIsbn postedBook = postBooks(token, prepareBodyToAddBooks(userId, collections));
        assertEquals(books.getBooks().stream().findFirst().get().getIsbn(), postedBook.getBooks().get(0).getIsbn(),
                "Book id is correct");
        GetUserResult userResult = getUser(token, userId);
        List<String> userBooks = userResult.getBooks().stream().map(x -> x.getIsbn()).collect(Collectors.toList());
        assertTrue(userBooks.contains(postedBook.getBooks().get(0).getIsbn()), "Book is added to collection");
        deleteUser(token, userId);
    }

    RegisterViewModel prepareBodyForUser() {
        RegisterViewModel body = new RegisterViewModel();
        body.setUserName(USERNAME);
        body.setPassword(PASSWORD);
        return body;
    }

    LoginViewModel prepareBodyForUserToken() {
        LoginViewModel body = new LoginViewModel();
        body.setUserName(USERNAME);
        body.setPassword(PASSWORD);
        return body;
    }

    AddListOffBooks prepareBodyToAddBooks(String userId, List<CollectionsOfIsbn> isbn) {
        AddListOffBooks body = new AddListOffBooks();
        body.setUserId(userId);
        body.setCollectionOfIsbns(isbn);
        return body;
    }

    CollectionsOfIsbn prepareCollection(String isbn) {
        CollectionsOfIsbn collection = new CollectionsOfIsbn();
        collection.setIsbn(isbn);
        return collection;
    }
}
