package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyWaits {

    public MyWaits() {
    }

    public boolean waitForElement(WebDriver driver, WebElement element, int secs) {
        return new WebDriverWait(driver, secs)
                .until(MyExpectedConditions.visibilityOfElement(element));
    }

    public boolean waitForElement(WebDriver driver, WebElement element) {
        return waitForElement(driver, element, 30);
    }

    public boolean waitForElement2go(WebDriver driver, WebElement element, int secs) {
        return new WebDriverWait(driver, secs)
                .until(MyExpectedConditions.invisibilityOfElement(element));
    }

    public boolean waitForElement2go(WebDriver driver, WebElement element) {
        return waitForElement(driver, element, 30);
    }

    public boolean waitForText(WebDriver driver, WebElement element, int secs, String expected) {
        return new WebDriverWait(driver, secs)
                .until(ExpectedConditions.textToBePresentInElement(element, expected));
    }

    public boolean waitForText(WebDriver driver, WebElement element, String expected) {
        return waitForText(driver, element, 30, expected);
    }

//    public void waitForSpinner(WebDriver driver, int secs) {
//        WebDriverWait wait = new WebDriverWait(driver, secs);
//        By locator = By.xpath("");
//        try {
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        } catch(Exception ignored) {
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        }
//    }
//
//    public void waitForSpinner(WebDriver driver) {
//        waitForSpinner(driver, 3);
//    }
}
