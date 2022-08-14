package helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MyExpectedConditions {

    public static boolean visibilityOf(WebElement element) {
        try {
            return element.isDisplayed();
        } catch(StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }

    public static ExpectedCondition<Boolean> visibilityOfElement(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return visibilityOf(element);
            }

            @Override
            public String toString() {
                return "visibility of " + element;
            }
        };
    }

    public static boolean invisibilityOf(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch(StaleElementReferenceException | NoSuchElementException e) {
            return true;
        }
    }

    public static ExpectedCondition<Boolean> invisibilityOfElement(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return invisibilityOf(element);
            }

            @Override
            public String toString() {
                return "invisibility of " + element;
            }
        };
    }
}
