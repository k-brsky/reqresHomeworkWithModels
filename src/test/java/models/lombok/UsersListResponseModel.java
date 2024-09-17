package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties({"page", "total_pages", "data", "support"})
public class UsersListResponseModel {
    @JsonProperty("total")
    private int total;
    @JsonProperty("per_page")
    private int perPage;
}
