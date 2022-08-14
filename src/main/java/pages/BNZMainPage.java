package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BNZMainPage extends Common {

    public BNZMain_SideMenu menu;



    public BNZMainPage(WebDriver driver, String folder) {
        super(driver);
        this.driver = driver;
        setScreenshotFolder(folder);
        PageFactory.initElements(driver, this);
        menu = new BNZMain_SideMenu(driver);
    }

    @FindBy(xpath = "//button[contains(@class, 'js-main-menu-btn')]")
    private WebElement menuButtonElement;



    private void clickMenu() {
        waits.waitForElement(driver, menuButtonElement);
        takeScreenShot(driver, "BNZ_LANDING_PAGE");
        menuButtonElement.click();
    }

    public PayeesPage gotoPayees() {
        clickMenu();
        waits.waitForElement(driver, menu.payeesMenuItemElement);
        menu.payeesMenuItemElement.click();
        PayeesPage payees = new PayeesPage(driver, screenshotFolder);
        waits.waitForElement(driver, payees.pageHeaderElement);
        takeScreenShot(driver, "PAYEES_PAGE");
        return payees;
    }

}
