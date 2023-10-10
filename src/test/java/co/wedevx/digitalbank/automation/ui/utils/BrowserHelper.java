package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WebDriver Helper Extensions is designed a simplify Java based Selenium/WebDriver tests.
 * It,s built on of Selenium/WebDriver to make your test more readable, reusable and
 * maintainable by providing easy handling towards browser and advance identifier.
 */
public class BrowserHelper {
    //wait until the element is visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element,Duration duration){
        WebDriverWait wait = new WebDriverWait(driver,duration);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    //wait until the element is clickable and click it
    public  static  WebElement waitUntilElementClickableAndClick(WebDriver driver,WebElement element,Duration duration){
        WebDriverWait wait = new WebDriverWait(driver,duration);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
        return clickableElement;
    }
}
