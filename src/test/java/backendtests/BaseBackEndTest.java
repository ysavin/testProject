package backendtests;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_CREATED;
import static org.apache.hc.core5.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

import backendtests.dto.AddListOffBooks;
import backendtests.dto.AllBooksModal;
import backendtests.dto.CreateUserResult;
import backendtests.dto.GetUserResult;
import backendtests.dto.LoginViewModel;
import backendtests.dto.NewCollectionOfIsbn;
import backendtests.dto.RegisterViewModel;
import backendtests.dto.TokenViewModel;

public class BaseBackEndTest {

    public BaseBackEndTest() {
    }

    private static final String HOST = "https://demoqa.com";
    private static final String USER = "/Account/v1/User";
    private static final String USER_BY_ID = "/Account/v1/User/{userId}";
    private static final String POST_GENERATE_TOKEN = "/Account/v1/GenerateToken";
    private static final String BOOKSTORE_BOOKS = "/BookStore/v1/Books";


    public CreateUserResult postUser(RegisterViewModel body) {
        return given().contentType("application/json")
                .body(body)
                .when().log().everything()
                .post(HOST + USER)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .as(CreateUserResult.class);
    }

    public TokenViewModel generateToken(LoginViewModel body) {
        return given()
                .contentType("application/json")
                .body(body)
                .when().log().everything()
                .post(HOST + POST_GENERATE_TOKEN)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .as(TokenViewModel.class);
    }

    public GetUserResult getUser(String token, String userId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when().log().everything()
                .get(HOST + USER_BY_ID, userId)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .as(GetUserResult.class);
    }

    public AllBooksModal getBooks() {
        return given()
                .when().log().everything()
                .get(HOST + BOOKSTORE_BOOKS)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .as(AllBooksModal.class);
    }

    public NewCollectionOfIsbn postBooks(String token, AddListOffBooks body) {
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when().log().everything()
                .body(body)
                .post(HOST + BOOKSTORE_BOOKS)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .as(NewCollectionOfIsbn.class);
    }

    public void deleteUser(String token, String userId) {
        given()
                .header("Authorization", "Bearer " + token)
                .when().log().everything()
                .delete(HOST + USER_BY_ID, userId)
                .then().log().everything()
                .assertThat()
                .statusCode(SC_NO_CONTENT);
    }
}
