package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage = new RegistrationPage(getDriver());

    List<Map<String,Object>> nextValList =new ArrayList<>();

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
     getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));
     assertEquals("Digital Bank",getDriver().getTitle(),"Registration page Title mismatch");
    }
    @When("User creates account with following fields")
    public void user_creates_account_with_following_fields(List<Map<String,String>> registrationTestDataListOfMap) {
        registrationPage.fillOutRegistrationForm(registrationTestDataListOfMap);
    }
    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessfulMessage) {
       assertEquals(expectedSuccessfulMessage,registrationPage.getMessage(),"SuccessMessage Mismatch");
    }

    @Then("The user should see the {string} required field error message {string}")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = registrationPage.getRequiredErrorMessage(fieldName);
        assertEquals(expectedErrorMessage,actualErrorMessage,"The error message of required" + fieldName + " field mismatch");
    }

    @Then("the following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String,String>> expectedUserProfileInfoInDBList) {
        Map<String,String> expectedUserInfoMap = expectedUserProfileInfoInDBList.get(0);
         String queryUserTable = String.format("select * from users where username='%s'",expectedUserInfoMap.get("email"));
         String queryUserProfile = String.format("select * from user_profile where email_address='%s'",expectedUserInfoMap.get("email"));

          List<Map<String,Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryUserTable);
          List<Map<String,Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfile);

        assertEquals(1,actualUserInfoList.size(),"Registration generated unexpected number of users");
        assertEquals(1,actualUserProfileInfoList.size(),"Registration generated unexpected number of users");

        Map<String,Object> actualUserInfoMap = actualUserInfoList.get(0);
        Map<String,Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);

        assertEquals(expectedUserInfoMap.get("title"),actualUserProfileInfoMap.get("title"),"Registration generated wrong title");
        assertEquals(expectedUserInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "Registration generated wrong firstName");
        assertEquals(expectedUserInfoMap.get("lastName"),actualUserProfileInfoMap.get("last_name"),"Registration generated wrong lastName");
        assertEquals(expectedUserInfoMap.get("gender"),actualUserProfileInfoMap.get("gender"),"Registration generated wrong gender");
       // assertEquals(expectedUserInfoMap.get("dob"),actualUserProfileInfoMap.get("dob"),"Registration generated wrong dob");
        assertEquals(expectedUserInfoMap.get("ssn"),actualUserProfileInfoMap.get("ssn"),"Registration generated wrong ssn");
        assertEquals(expectedUserInfoMap.get("email"),actualUserProfileInfoMap.get("email_address"),"Registration generated wrong email");
        assertEquals(expectedUserInfoMap.get("address"),actualUserProfileInfoMap.get("address"),"Registration generated wrong address");
        assertEquals(expectedUserInfoMap.get("locality"),actualUserProfileInfoMap.get("locality"),"Registration generated wrong locality");
        assertEquals(expectedUserInfoMap.get("region"),actualUserProfileInfoMap.get("region"),"Registration generated wrong region");
        assertEquals(expectedUserInfoMap.get("postalCode"),actualUserProfileInfoMap.get("postal_code"),"Registration generated wrong postalCode");
        assertEquals(expectedUserInfoMap.get("country"),actualUserProfileInfoMap.get("country"),"Registration generated wrong country");
        assertEquals(expectedUserInfoMap.get("homePhone"),actualUserProfileInfoMap.get("home_phone"),"Registration generated wrong homePhone");
        assertEquals(expectedUserInfoMap.get("mobilPhone"),actualUserProfileInfoMap.get("mobil_phone"),"Registration generated wrong mobilPhone");
        assertEquals(expectedUserInfoMap.get("workPhone"),actualUserProfileInfoMap.get("work_phone"),"Registration generated wrong workPhone");

        //validate users table

        assertEquals(expectedUserInfoMap.get("accountNonExpired"),String.valueOf(actualUserInfoMap.get("account_non_expired")),"accountNonExpired mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("accountNonLocked"),String.valueOf(actualUserInfoMap.get("account_non_locked")),"accountNonLocked mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("credentialsNonExpired"),String.valueOf(actualUserInfoMap.get("credentials_non_expired")),"credentialsNonExpired mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("enabled"),String.valueOf(actualUserInfoMap.get("enabled")),"account enable mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("email"),actualUserInfoMap.get("username"),"username mismatch upon registration");

        assertEquals(nextValList.get(0).get("next_val"),actualUserInfoMap.get("id"),"Id mismatch");
        long expectedUserProfileId = Long.parseLong(String.valueOf(nextValList.get(0).get("next_val")));
        assertEquals(++expectedUserProfileId,actualUserProfileInfoMap.get("id"),"Id mismatch");
    }

    @Given("The user with {string} is not in DB")
    public void theUserWithIsNotInDB(String email) {

        String queryForUserProfile = String.format("DELETE FROM user_profile WHERE email_address='%s'",email);
        String queryForUsers = String.format("DELETE FROM users WHERE username='%s'",email);

        String queryToGetNextInHibernateSeqTable = String.format("select *  from hibernate_sequence");
        nextValList = DBUtils.runSQLSelectQuery(queryToGetNextInHibernateSeqTable);

        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);
    }
}
