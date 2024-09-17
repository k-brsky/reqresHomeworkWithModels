package tests;

import io.restassured.RestAssured;
import models.lombok.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqSpecs.*;

@Tag("api")
public class ReqresTests extends TestBase{

    @Test
    void countOfUsersTest() {
        UsersListResponseModel userList = step("Make request", () ->
                given(requestSpecNoJson)
                        .get("/users?page=2")
                        .then()
                        .extract().as(UsersListResponseModel.class));
                step("Check count of users", () -> {
                        assertEquals(6, userList.getPerPage());
                        assertEquals(12, userList.getTotal());
                });
    }

    @Test
    void userDataTest() {
        UserDataResponseModel userData = step("Make request", () ->
                given(requestSpecNoJson)
                        .get("/users/2")
                        .then()
                        .extract().as(UserDataResponseModel.class));
                step("Check response", () -> {
                        assertEquals(2, userData.getData().getId());
                        assertEquals("janet.weaver@reqres.in", userData.getData().getEmail());
                        assertEquals("Janet", userData.getData().getFirstName());
                        assertEquals("Weaver", userData.getData().getLastName());
                        assertEquals("https://reqres.in/img/faces/2-image.jpg", userData.getData().getAvatar());
                        assertEquals("https://reqres.in/#support-heading", userData.getSupport().getUrl());
                        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", userData.getSupport().getText());
                });
    }

    @Test
    void deleteStatusCodeTest() {
        step("Make delete request", () ->
                given(requestSpecNoJson)
                        .delete("/users/2")
                        .then()
                        .spec(deleteResponseSpecification204)
                        .extract().asString());
        step("Check delete response", () ->
                given(requestSpecNoJson)
                        .delete("/users/2")
                        .then()
                        .spec(deleteResponseSpecification204));
    }

    @Test
    void registerUserTest() {
        RegisterUserRequestModel userData = new RegisterUserRequestModel();
        userData.setEmail("eve.holt@reqres.in");
        userData.setPassword("pistol");

        RegisterUserResponseModel response = step("Make registration request", () ->
                given(requestSpecJson)
                        .body(userData)
                        .post("/register")
                        .then()
                        .spec(successfulResponseSpecification)
                        .extract().as(RegisterUserResponseModel.class));
        step("Check register response", () -> {
                assertEquals(4, response.getId());
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }

    @Test
    void createUserTest() {
        CreateUserRequestModel userData = new CreateUserRequestModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        CreateUserResponseModel response = step("Make creating request", () ->
                given(requestSpecJson)
                        .body(userData)
                        .post("/users")
                        .then()
                        .extract().as(CreateUserResponseModel.class));
        step("Check creating request", () -> {
                assertEquals("morpheus", response.getName());
                assertEquals("leader", response.getJob());
                assertThat(response.getId()).isNotNull();
                });
    }
}
