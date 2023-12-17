package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.UserAPIModel;
import co.wedevx.digitalbank.automation.api.models.UserDomainForDataTable;
import static co.wedevx.digitalbank.automation.api.utils.ObjectMapperUtils.objectMapper;
import co.wedevx.digitalbank.automation.api.utils.RestHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import java.util.List;

public class UserSteps {

    public  static int testUserId = 0;
    @Given("the following user is in db")
    public void the_following_user_is_in_db(List<UserDomainForDataTable> userDomainForDataTables) throws JsonProcessingException {

/**
        // System.out.println(users);

        RestAssured.baseURI = "http://kubedinbakalovv.mydevx.com/bank/api/v1";
        //we need to get authentication token like string with JsonPath
        Response autRequestResponse = given()
                .queryParam("username","admin@demo.io")
                .queryParam("password","Demo123!")
                .when()
                .post("auth");

        JsonPath autResponseJsonPath = autRequestResponse.jsonPath();

        String authToken = autResponseJsonPath.getString("authToken");
*/
        //System.out.println(authToken);
        //convert the POJO of users into a single user json string
/**
        ObjectMapper objectMapper = new ObjectMapper();
        String createUserBodyPayload = objectMapper.writeValueAsString(users.get(0));

        System.out.println(createUserBodyPayload);

        Response createUserRequestResponse = given()
                .header("Authorization","Bearer " + authToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createUserBodyPayload)
                .when()
                .post("user");

        createUserRequestResponse.prettyPrint();
 */

        String createUserBodyPayload = objectMapper.writeValueAsString(userDomainForDataTables.get(0));

        Response response = RestHttpRequest.requestSpecification
                .body(createUserBodyPayload)
                .when()
                .post("user");

       response.prettyPrint();

        UserAPIModel testUser = objectMapper.readValue(response.asString(), UserAPIModel.class);

        System.out.println(testUser.getId());
        testUserId = testUser.getId();

        System.out.println(testUser.getUsername());
    }
}

/**
 baseURI -> take from config
 generated auth token only once per the whole test suite run.
 apply generated auth token to all requests
 more objectMapper to utils class to make sure we are creating the objectMapper object only once

 */
