package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }
    //we find all element on the page
    @FindBy(id="username")
    private WebElement usernameTxBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "remember-me")
    private WebElement remember_meCheckBox;

    @FindBy(xpath = "//button")
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(text), 'Sign Up Here']")
    private WebElement singUpHereLink;
    //we need to create action methods
    public void login(String username,String password){
        usernameTxBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitBtn.click();
    }
}
