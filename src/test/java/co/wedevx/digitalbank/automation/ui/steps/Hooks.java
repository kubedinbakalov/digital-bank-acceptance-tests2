package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @BeforeAll()
    public static void establishConnectionToDB(){
        DBUtils.establishConnection();
        DBUtils.runSQLUpdateQuery("DELETE FROM user_profile where email_address");
    }

    @Before("not @Registration")
    public void the_user_is_on_d_bank_homepage(){

        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
    }

    @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario) {
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }

    @AfterAll()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }
}
