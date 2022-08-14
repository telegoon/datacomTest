package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class PayeesPage extends Common {

    public PayeesPage(WebDriver driver, String folder) {
        super(driver);
        PageFactory.initElements(driver, this);
        setScreenshotFolder(folder);
    }

    @FindBy(xpath = "//header[contains(@aria-label, 'Payees page')]")
    protected WebElement pageHeaderElement;

    @FindBy(xpath = "//span[text() = 'Payees']")
    protected WebElement pageTitleElement;

    @FindBy(xpath = "//button[contains(@class, 'js-add-payee')]")
    private WebElement addButtonElement;

    @FindBy(xpath = "//input[contains(@id, 'Input-apm-name')]")
    private WebElement addPayeeNameInputElement;

    @FindBy(xpath = "//input[contains(@aria-label, 'New account')]")
    private List<WebElement> addPayeeBankAccountInputElements;

    @FindBy(xpath = "//button[contains(@class, 'js-submit')]")
    private WebElement submitButtonElement;

    @FindBy(xpath = "//span[@class = 'message']")
    private WebElement messageTextElement;

    @FindBy(xpath = "//div[@class = 'Payees js-payees']//ul")
    private WebElement payeesListElement;

    private boolean addPayeeName(String name) {
        addPayeeNameInputElement.sendKeys(name + Keys.ENTER);
        takeScreenShot(driver, "ADD_PAYEES_NAME");
        return (addPayeeNameInputElement.getAttribute("value").equals(name));
    }

    private boolean enterAccount(WebElement element, String part) {
        element.sendKeys(part);
        return element.getAttribute("value").equals(part);
    }

    private void addPayeeAccount(String account) {
        String[] accountSplit = account.split("-");
        for (int i = 0; i < accountSplit.length; i++) {
            Assert.assertTrue(enterAccount(addPayeeBankAccountInputElements.get(i), accountSplit[i]));
        }
        addPayeeBankAccountInputElements.get(addPayeeBankAccountInputElements.size() - 1).sendKeys(Keys.ENTER);
        takeScreenShot(driver, "ADD_PAYEES_ACCOUNT");
    }


    public boolean isPayeePageDisplayed() {
        return pageTitleElement.isDisplayed();
    }

    public boolean addPayee(String name, String account) {
        addButtonElement.click();
        waits.waitForElement(driver, addPayeeNameInputElement);
        Assert.assertTrue(addPayeeName(name));
        addPayeeAccount(account);
        submitButtonElement.click();
        waits.waitForElement(driver, messageTextElement);
        takeScreenShot(driver, "PAYEE_ADDED_MESSAGE");
        return messageTextElement.getText().equals("Payee added");
    }

    public boolean verifyPayeeAddToList(String name, String account) {
        for (WebElement listItem: payeesListElement.findElements(By.tagName("li"))) {
            if (listItem.findElement(By.xpath(".//span[@class = 'js-payee-name']")).getText().equals(name)) {
                return listItem.findElement(By.xpath(".//p[contains(@class, 'js-payee-account')]")).getText().equals(account);
            }
        }
        return false;
    }
}
