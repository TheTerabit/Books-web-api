package pl.bs.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String id;
    private VolumeInfo volumeInfo;

    public Book (@JsonProperty("id") String id,
                 @JsonProperty("volumeInfo") VolumeInfo volumeInfo){
        this.id=id;
        this.volumeInfo=volumeInfo;
    }

}
