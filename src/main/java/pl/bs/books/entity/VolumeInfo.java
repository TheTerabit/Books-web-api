package pl.bs.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//?
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
    private List<IndustryIdentifier> industryIdentifiers;
    private String title;
    private String subtitle;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private Double averageRating;
    private List<String> authors;
    private List<String> categories;

    public VolumeInfo(@JsonProperty("industryIdentifiers") List<IndustryIdentifier> industryIdentifiers,
                @JsonProperty("title") String title,
                @JsonProperty("subtitle") String subtitle,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("publishedDate") String publishedDate,
                @JsonProperty("description") String description,
                @JsonProperty("pageCount") Integer pageCount,
                @JsonProperty("imageLinks") ImageLinks imageLinks,
                @JsonProperty("language") String language,
                @JsonProperty("previewLink") String previewLink,
                @JsonProperty("averageRating") Double averageRating,
                @JsonProperty("authors") List<String> authors,
                @JsonProperty("categories") List<String> categories) {
        this.industryIdentifiers = industryIdentifiers;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
        this.averageRating = averageRating;
        this.authors = authors;
        this.categories = categories;
    }

}
