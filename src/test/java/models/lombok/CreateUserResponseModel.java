package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties("createdAt")
public class CreateUserResponseModel {
    String name, job;
    int id;
}
