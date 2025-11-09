package BaseFolder.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIBase {

    protected String baseUrl;

    /**
     * @Author : Yul Omar Villanueva
     * @Description : API Base Class to be extended by other API classes
     * @param baseUrl
     */
    public APIBase(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    /**
     * @Author : Yul Omar Villanueva
     * @Description : POST Request Method
     * @param endpoint : API Endpoint
     * @param body : Request Body
     * @return : Response
     */
    protected Response post(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()       // log request
                .when()
                .post(endpoint)
                .then()
                .log().all()       // log response
                .extract()
                .response();
    }

    /**
     * @Author : Yul Omar Villanueva
     * @Description : GET Request Method
     * @param endpoint : API Endpoint
     * @return : Response
     */
    protected Response get(String endpoint, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
