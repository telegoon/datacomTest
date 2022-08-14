package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BNZMain_SideMenu {

    public BNZMain_SideMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href, 'payees')]")
    protected WebElement payeesMenuItemElement;

    @FindBy(xpath = "//span[contains(text(), 'Pay or transfer')]/parent::button")
    protected WebElement paymentMenuItemElement;



}
