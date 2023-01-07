package backendtests.dto;

import java.util.List;
import lombok.Data;

@Data
public class GetUserResult {

    private String userId;
    private String username;
    private List<BookModal> books;


}
