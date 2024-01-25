package co.wedevx.digitalbank.automation.api.utils;

import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestHttpRequest {
    /**
     baseURI -> take from config
     generated auth token only once per the whole test suite run.
     apply generated auth token to all requests
     more objectMapper to utils class to make sure we are creating the objectMapper object only once

     */
    //baseURI -> take from config

    public static RequestSpecification requestSpecification = RestAssured.given();

    //generated auth token only once per the whole test suite run.
    //authToken is auto generated ,no need to generate it again

    public final static String authToken;

    // it is static instance initializers and this block of code,
    // it runs once for the whole project. When it's not static,
    // it initializes once for every object you create.  We don't need a static method addHeaders,
    //initialize run this bloc of code once the project starts it's guaranteed that authentication token will be generated

    static {

        requestSpecification.baseUri(ConfigReader.getPropertiesValue("digitalbank.base.api.uri"));

        authToken = generateAuthToken();

        addHeaders();
    }
/**
    public static void addHeaders(){

        requestSpecification.baseUri(ConfigReader.getPropertiesValue("digitalbank.base.api.uri"));
    }
 */

// We make this method private because we don't want ton call this method after initialize run.
    private static String generateAuthToken(){

        //we need to get authentication token like string with JsonPath
        Response autRequestResponse = requestSpecification
                .queryParam("username",ConfigReader.getPropertiesValue("digitalbank.api.admin.username"))
                .queryParam("password",ConfigReader.getPropertiesValue("digitalbank.api.admin.password"))
                .when()
                .post("auth");
        JsonPath autResponseJsonPath = autRequestResponse.jsonPath();

        return autResponseJsonPath.getString("authToken");
    }

    private
    static void addHeaders(){

        requestSpecification.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization","Bearer " + authToken);
    }

    public static Response sendPostRequestWithPathParam(String endpoint,String pathParamId,Object pathParamValue,String body){

       return requestSpecification
                .body(body)
                .pathParam(pathParamId,pathParamValue)
                .when()
                .post(endpoint);
    }

}
