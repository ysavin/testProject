package backendtests.dto;

import java.util.List;
import lombok.Data;

@Data
public class AddListOffBooks {

    private String userId;
    private List<CollectionsOfIsbn> collectionOfIsbns;
}
