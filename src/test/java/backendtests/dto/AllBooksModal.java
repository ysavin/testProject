package backendtests.dto;

import java.util.List;
import lombok.Data;

@Data
public class AllBooksModal {

    private List<BookModal> books;
}
