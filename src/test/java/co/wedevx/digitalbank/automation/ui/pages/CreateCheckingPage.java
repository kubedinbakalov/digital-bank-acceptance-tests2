package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountData;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage {

    public CreateCheckingPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingAccountRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxt;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxt;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitButton;

    public void createNewChecking(List<NewCheckingAccountData> newCheckingAccountDataList){

        NewCheckingAccountData testDataForOneCheckingAccount = newCheckingAccountDataList.get(0);

        checkingMenu.click();
        newCheckingButton.click();

        assertEquals(ConfigReader.getPropertiesValue("digialbank.createnewcheckingurl"),getDriver().getCurrentUrl(),"New Checking button take to the "+ConfigReader.getPropertiesValue("digialbank.createnewcheckingurl"));

        if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
            standardCheckingAccountRadioButton.click();
        }
        else if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")){
            interestCheckingAccountTypeRadioButton.click();
        }
        else {
           // System.out.println("Wrong account type");
            throw new NoSuchElementException("Invalid checking account type option provided. Only supports Standard Checking and Interest Checking");
        }

        if (testDataForOneCheckingAccount.getAccountOwnerShip().equalsIgnoreCase("Individual")){
            individualOwnershipTypeRadioButton.click();
        }
        else if (testDataForOneCheckingAccount.getAccountOwnerShip().equalsIgnoreCase("Joint")){
            jointOwnershipTypeRadioButton.click();
        }
        else {
            // System.out.println("Wrong account type");
            throw new NoSuchElementException("Invalid ownership type option provided. Only supports Individual Checking and Joint Checking");
        }
        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());

        openingBalanceTxt.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));

        submitButton.click();
        }
    }

