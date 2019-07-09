package pl.bs.books.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessedBook {

    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private List<String> authors;
    private List<String> categories;

    public ProcessedBook(Book book) throws ParseException {

        this.isbn = chooseIsbn(book);
        this.title = book.getVolumeInfo().getTitle();
        this.subtitle = book.getVolumeInfo().getSubtitle();
        this.publisher = book.getVolumeInfo().getPublisher();
        this.publishedDate = parsePublishedDate(book.getVolumeInfo().getPublishedDate());
        this.description = book.getVolumeInfo().getDescription();
        this.pageCount = book.getVolumeInfo().getPageCount();
        this.thumbnailUrl = book.getVolumeInfo().getImageLinks().getThumbnail();
        this.language = book.getVolumeInfo().getLanguage();
        this.previewLink = book.getVolumeInfo().getPreviewLink();
        this.averageRating = book.getVolumeInfo().getAverageRating();
        this.authors = book.getVolumeInfo().getAuthors();
        this.categories = book.getVolumeInfo().getCategories();
    }

    private Long parsePublishedDate(String publishedDate) throws ParseException {

        if(publishedDate != null) {
            DateFormat formatterYear = new SimpleDateFormat("yyyy");
            DateFormat formatterYMD = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            if (publishedDate.length() < 9)
                date = formatterYear.parse(publishedDate);
            else
                date = formatterYMD.parse(publishedDate);

            return date.getTime();
        }
        else
            return null;
    }

    private String chooseIsbn(Book book){

        String isbn=book.getId();
        if(book.getVolumeInfo().getIndustryIdentifiers().size()!=0)
        {
            for(IndustryIdentifier industryIdentifier : book.getVolumeInfo().getIndustryIdentifiers()){
                isbn=industryIdentifier.getIdentifier();

                if(industryIdentifier.getType().equals("ISBN_13"))
                    break;
            }
        }

        return isbn;
    }
}
