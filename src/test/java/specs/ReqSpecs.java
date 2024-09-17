package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class ReqSpecs {
    public static RequestSpecification requestSpecJson = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static RequestSpecification requestSpecNoJson = with()
            .filter(withCustomTemplates())
            .log().all();

    public static ResponseSpecification deleteResponseSpecification204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .build();
    public static ResponseSpecification successfulResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
