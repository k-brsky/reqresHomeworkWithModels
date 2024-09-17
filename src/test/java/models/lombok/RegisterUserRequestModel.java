package models.lombok;

import lombok.Data;

@Data
public class RegisterUserRequestModel {
    String email, password;
}
