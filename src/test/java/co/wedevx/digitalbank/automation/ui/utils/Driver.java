package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private Driver(){
    }
    public static WebDriver getDriver(){
        if (driver == null){
            switch (ConfigReader.getPropertiesValue("browser").toLowerCase()) {
                case"chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver =  new SafariDriver();
                    break;
                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("disable-extensions");
                   // chromeOptions.setExperimentalOption("userAutomationExtensions",false);
                    //chromeOptions.addArguments("--proxy-server='direct://'");
                    chromeOptions.addArguments("--proxy_bypass-list=*");
                    chromeOptions.addArguments("--start=maximized");
                    chromeOptions.addArguments("--headless");

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "ie":
                default:
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }
    public static void takeScreenShot(Scenario scenario){
        if (scenario.isFailed()) {
            //taking a screenshot
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot,"image/png","screenshot");
        }
    }
    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
