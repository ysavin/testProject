package backendtests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookModal {

    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonProperty("publish_date")
    private String publishDate;
    private String publisher;
    private float pages;
    private String description;
    private String website;
}
