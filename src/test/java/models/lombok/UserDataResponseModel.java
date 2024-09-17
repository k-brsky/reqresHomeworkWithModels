package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDataResponseModel {
    private DataInfo data;
    private Support support;

    @Data
    public static class DataInfo {
        private int id;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String avatar;
    }

    @Data
    public static class Support {
        private String url;
        private String text;
    }
}
