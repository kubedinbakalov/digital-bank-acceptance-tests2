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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
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
                case "saucelabs":
                    //1 we use configReader and properties
                  //  String platform = ConfigReader.getPropertiesValue("dbank.saucelabs.platform");
                  //  String browser = ConfigReader.getPropertiesValue("dbank.saucelabs.browser");
                  //  String browserVersion = ConfigReader.getPropertiesValue("dbank.saucelabs.browser.version");
                    // 2 we use Edit Configuration(Run/Debug Configuration) Feature or folder path
                    String platform = System.getProperty("dbank.saucelabs.platform");
                    String browser = System.getProperty("dbank.saucelabs.browser");
                    String browserVersion = System.getProperty("dbank.saucelabs.browser.version");

                    driver = loadSauceLabs(platform,browser,browserVersion);
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
            //taking a screenshot and saving it in byte arrays.
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot,"image/png","screenshot");
        }
    }
    //Create a method that quits the driver
    //which should check if instance is already instantiated once
    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browser, String browserVersion) {

        //How to use SauceLabs
        //First get sauceLabs userName and access key

        String USERNAME = ConfigReader.getPropertiesValue("dbank.saucelabs.username");
        String ACCESS_KEY = ConfigReader.getPropertiesValue("dbank.saucelabs.accesskey");
        String HOST = ConfigReader.getPropertiesValue("dbank.saucelabs.host");

        //setup url to the hub which is running on sauceLabs VMs.

        String url = "https://" + USERNAME + ":" + ACCESS_KEY + "@" + HOST;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion",browserVersion);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
           e.printStackTrace();
        }
        return driver;
    }
}
