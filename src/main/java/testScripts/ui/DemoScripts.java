package testScripts.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BNZMainPage;
import pages.PayeesPage;
import testScripts.TestSuite;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class DemoScripts extends TestSuite {
    final Logger LOGGER = LoggerFactory.getLogger(TestSuite.class);

    public DemoScripts() throws FileNotFoundException {
        super();
    }

    @BeforeMethod
    public void setup(Method method) {
        driver = setup();
        LOGGER.info("Running Test: " + method.getName());
        createResultFolder(method.getName());
    }

    @Test(priority=1)
    public void verifyPayeeNavigation() {
        BNZMainPage land = new BNZMainPage(driver, screenshotFolder);
        PayeesPage payees = land.gotoPayees();
        Assert.assertTrue(payees.isPayeePageDisplayed());
    }

    @Test(priority=2)
    public void verifyPayeeAdd() {
        BNZMainPage land = new BNZMainPage(driver, screenshotFolder);
        PayeesPage payees = land.gotoPayees();
        Assert.assertTrue(payees.addPayee("Fred Flintstone", "01-2345-1234567-89"));
        Assert.assertTrue(payees.verifyPayeeAddToList("Fred Flintstone", "01-2345-1234567-89"));
    }
}
