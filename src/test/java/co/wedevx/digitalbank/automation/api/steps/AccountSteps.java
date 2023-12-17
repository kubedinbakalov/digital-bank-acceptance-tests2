package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.AccountModel;
import co.wedevx.digitalbank.automation.api.models.AccountResponseModel;
import co.wedevx.digitalbank.automation.api.models.AccountTypeModel;
import co.wedevx.digitalbank.automation.api.utils.ObjectMapperUtils;
import co.wedevx.digitalbank.automation.api.utils.RestHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountSteps {

    private AccountResponseModel accountResponseModel;

    @When("the following banking account is created")
    public void the_following_banking_account_is_created(List<AccountModel> accountModelList) throws JsonProcessingException {

        //System.out.println(accountModelList);

       String accountRequestBodyJson = ObjectMapperUtils.objectMapper.writeValueAsString(accountModelList.get(0));
/**
        Response createAccountResponse = RestHttpRequest.requestSpecification
                .body(accountRequestBodyJson)
                .pathParam("id",UserSteps.testUserId)
                .when()
                .post("/user/{id}/account");
*/

        Response createAccountResponse = RestHttpRequest.sendPostRequestWithPathParam("/user/{id}/account",
                "id",UserSteps.testUserId,accountRequestBodyJson);

        //System.out.println(createAccountResponse.asString());

        createAccountResponse.prettyPrint();

        accountResponseModel = ObjectMapperUtils.objectMapper.readValue(createAccountResponse.asString(),AccountResponseModel.class);

    }

    @Then("the following account details are returned in the response")
    public void theFollowingAccountDetailsAreReturnedInTheResponse(List<AccountModel> accountModelList) {

        assertEquals(accountModelList.get(0).getAccountName(),accountResponseModel.getName(),"Account Name mismatch after creating an account");

        assertEquals(accountModelList.get(0).getAccountTypeCode(),accountResponseModel.getAccountType().getCode(),"Account Type code mismatch after creating an account");

        assertEquals(accountModelList.get(0).getOpeningDeposit(),accountResponseModel.getOpeningBalance(),"Account Opening Deposit mismatch after creating an account");

        assertEquals(accountModelList.get(0).getOwnerTypeCode(),accountResponseModel.getOwnershipType().getCode(),"Account Ownership Type code mismatch after creating an account");

        assertEquals(accountModelList.get(0).getAccountStandingName(),accountResponseModel.getAccountStanding().getName(),"Account Standing Name mismatch after creating an account");

        // We need to validate AccountType,

        if(accountModelList.get(0).getAccountTypeCode().equalsIgnoreCase("SCK")){

           AccountTypeModel expectedAccountTypeModel = AccountTypeModel.createDefaultSCKAccountTypeModel();

            assertEquals(expectedAccountTypeModel.getId(),accountResponseModel.getAccountType().getId());
            assertEquals(expectedAccountTypeModel.getCode(),accountResponseModel.getAccountType().getCode());
            assertEquals(expectedAccountTypeModel.getCategory(),accountResponseModel.getAccountType().getCategory());
            assertEquals(expectedAccountTypeModel.getName(),accountResponseModel.getAccountType().getName());
            assertEquals(expectedAccountTypeModel.getInterestRate(),accountResponseModel.getAccountType().getInterestRate());
            assertEquals(expectedAccountTypeModel.getMinDeposit(),accountResponseModel.getAccountType().getMinDeposit());
            assertEquals(expectedAccountTypeModel.getOverdraftFee(),accountResponseModel.getAccountType().getOverdraftFee());
        }
        // to validate OwnershipType,
        // to validate AccountStanding

        }
    @Then("the following account details in the db")
    public void the_following_account_details_in_the_db() {

    }
}

