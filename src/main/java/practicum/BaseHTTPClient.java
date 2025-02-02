package practicum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static practicum.Constants.*;

public abstract class BaseHTTPClient {

    private RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(HOST)
            .addHeader(CONTENT_TYPE_HEADER_NAME, JSON_HEADER_VALUE)
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    protected Response makeGetRequest(String path, String token){
        return given().spec(baseRequestSpec).auth().oauth2(token).get(path).thenReturn();
    }

    protected Response makePostRequest(String path, String token, Object body){
        return given().spec(baseRequestSpec).auth().oauth2(token).body(body).post(path).thenReturn();
    }

    protected Response makePostRequest(String path, Object body){
        return given().spec(baseRequestSpec).body(body).post(path).thenReturn();
    }

    protected Response makePutRequest(String path, String token, Object body){
        return given().spec(baseRequestSpec).auth().oauth2(token).body(body).put(path).thenReturn();
    }

    protected Response makeDeleteRequest(String path, String token){
        return given().spec(baseRequestSpec).auth().oauth2(token).delete(path).thenReturn();
    }
}
