package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {
    //private static final Logger LOGGER = LoggerFactory.getLogger(browser.class);
    static WebDriver driver;
    static final String pathName = "resources/.";


    /**
     * Initiate the test firefox profile with client side Authentication
     * @return FirefoxOptions object with the test firefox profile attached
     */
    private static FirefoxOptions setupFirefoxProfile() {
       // LOGGER.info("Initiating Firefox user profile");
//        File profileFile = new File(pathName);
        FirefoxProfile profile = new FirefoxProfile();

        // Set profile to automatically accept personal authentication certificates
        profile.setPreference("security.default_personal_cert", "Select Automatically");
        profile.setPreference("intl.accept_languages", "en-gb");

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
//        options.addPreference("browser.download.dir", "/usr/downloads");
//        options.addPreference("browser.download.folderList", 2);
//        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/html,application/octet-stream");

        return options;
    }

    /**
     * initiate the WebDriver (firefox only at this stage) page load time set to 20 and implicit wait time set to 5
     * @param driverPath the path to the gecko driver (firefox driver)
     * @return an instance of the WebDriver
     */
    public static WebDriver initWebDriver(String driverPath) {
        return initWebDriver(driverPath, 20, 5);
    }

    /**
     * initiate the WebDriver (firefox only at this stage)
     * @param driverPath the path to the gecko driver (firefox driver)
     * @param pageLoad integer of the time to wait for a page load
     * @param implicitWait integer of the the to wait for an element to appear
     * @return an instance of the WebDriver
     */
    public static WebDriver initWebDriver(String driverPath, int pageLoad, int implicitWait) {
       // LOGGER.info("Initiating Firefox Driver");
        System.setProperty("webdriver.gecko.driver", driverPath);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");

        driver = new FirefoxDriver(setupFirefoxProfile());

        // set default driver options
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(pageLoad, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        return driver;
    }

}
