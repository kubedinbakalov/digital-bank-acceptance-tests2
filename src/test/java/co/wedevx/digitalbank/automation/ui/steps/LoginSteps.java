package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
   // @BeforeAll
    public static void setup() {
        WebDriverManager.firefoxdriver().setup();
    }
    WebDriver driver = Driver.getDriver();

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {

        driver.get("http://kubedinb509.mydevx.com/bank/login");
    }
    @Given("the user populates the username field with {string}")
    public void the_user_populates_the_username_field_with(String username) {
        WebElement userNameTxt = driver.findElement(By.name("username"));
        userNameTxt.sendKeys(username);
    }
    @Given("the user populates the password field with {string}")
    public void the_user_populates_the_password_field_with(String password) {
        WebElement passwordTxt = driver.findElement(By.name("password"));
        passwordTxt.sendKeys(password);
    }
    @When("the user clicks the sign-in button")
    public void the_user_clicks_the_sign_in_button() {
        WebElement singInButton = driver.findElement(By.id("submit"));
        singInButton.click();
    }
    @Then("user should see user verify massage {string}")
    public void user_should_see_user_verify_massage(String massage) {
        WebElement massages = driver.findElement(By.xpath("//li[@class='active']"));
        System.out.println(massages.getText());
        assertEquals(massage,massages.getText());
    }
    @Given("the user populates the password field with an incorrect password {string}")
    public void the_user_populates_the_password_field_with_an_incorrect_password(String password) {
        WebElement passwordTxt = driver.findElement(By.name("password"));
        passwordTxt.sendKeys(password);
    }
    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String errorMessages) {
        WebElement errorMessage = driver.findElement(By.xpath("//div/span"));
        System.out.println(errorMessage.getText());
        assertEquals(errorMessages,errorMessage.getText());
    }
    @Given("the user populates a non-existent username {string}")
    public void the_user_populates_a_non_existent_username(String username) {
        WebElement userNameTxt = driver.findElement(By.name("username"));
        userNameTxt.sendKeys(username);
    }
}
