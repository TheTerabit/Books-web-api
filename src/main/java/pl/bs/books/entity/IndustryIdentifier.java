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
public class IndustryIdentifier {
    private String type;
    private String identifier;

    public IndustryIdentifier(@JsonProperty("type") String type,
                              @JsonProperty("identifier") String identifier){
        this.type=type;
        this.identifier=identifier;
    }
}
