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
public class JsonResponse {
    List<Book> books;
    public JsonResponse(@JsonProperty("items") List<Book> books){
        this.books = books;
    }
}
