package backendtests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CreateUserResult {

    @JsonProperty("userID")
    private String userId;
    private String username;
    private List<BookModal> books;
}
