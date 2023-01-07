package backendtests.dto;

import java.util.List;
import lombok.Data;

@Data
public class NewCollectionOfIsbn {

    List<CollectionsOfIsbn> books;
}
