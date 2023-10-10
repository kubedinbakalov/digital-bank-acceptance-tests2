package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountData;
import co.wedevx.digitalbank.automation.ui.models.Transactions;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewCheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
  // WebDriver driver = new FirefoxDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private CreateCheckingPage createNewChecking = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String userName, String password) {

        loginPage.login(userName,password);
    }
    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountData> newCheckingAccountDataList) {
        createNewChecking.createNewChecking(newCheckingAccountDataList);

    }
    @Then("the user should see the green {string} message.")
    public void the_user_should_see_the_green_message(String expectedConfirmationMessage) {

        expectedConfirmationMessage = "Confirmation " + expectedConfirmationMessage + "\n×";
        assertEquals(expectedConfirmationMessage,viewCheckingAccountPage.getAccountCreateAccountConfirmationMessage());
    }
    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {

        Map<String,String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();

        AccountCard expectedResult = accountCardList.get(0);
        assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        assertEquals("Account: " + expectedResult.getAccountType(),actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: " + expectedResult.getOwnership(),actualResultMap.get("actualOwnership"));
        assertEquals("Interest Rate: " + expectedResult.getInterestRate(),actualResultMap.get("actualInterestRate"));
        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $" + expectedBalance, actualResultMap.get("actualBalance"));

    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<Transactions> expectedTransactions) {

       Map<String,String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingTransactionInfoMap();

        Transactions expectedTransaction = expectedTransactions.get(0);
        // assertEquals(expectedTransaction.getDate(), actualDate);
       assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"),"Transaction category mismatch");
        //assertEquals(expectedTransaction.getDescription(), actualDescription);
        assertEquals(expectedTransaction.getAmount(),Double.parseDouble(actualResultMap.get("actualAmountText")),"Transaction amount mismatch"); // Adjust tolerance as needed
        assertEquals(expectedTransaction.getBalance(),Double.parseDouble(actualResultMap.get("actualBalanceText")),"Transaction balance mismatch"); // Adjust tolerance as needed
/**
          fail();//It needs for screenshot
*/
    }
}
