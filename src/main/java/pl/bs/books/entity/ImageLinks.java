package pl.bs.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//?
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinks {
    private String thumbnail;
    ImageLinks(@JsonProperty("thumbnail") String thumbnail){
        this.thumbnail=thumbnail;
    }
}
