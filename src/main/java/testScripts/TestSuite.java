package testScripts;


import ch.qos.logback.classic.util.ContextInitializer;
import com.google.gson.Gson;

import helpers.Config;
import helpers.Browser;

import helpers.MyWaits;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestSuite {
    final Logger LOGGER = LoggerFactory.getLogger(TestSuite.class);
    final Gson gson = new Gson();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");
    LocalDateTime now;
    PrintStream resultsFile;
    public String screenshotFolder;
    public MyWaits waits = new MyWaits();
    public WebDriver driver;
    public Config config;

    public WebDriver setup() {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "src/resources/logback.xml");
        LOGGER.info("Running Setup Operations");
        driver = Browser.initWebDriver(config.getDriverPath());
        driver.get(config.getDemoURL());
        this.screenshotFolder = config.getResultsDir() +
                new SimpleDateFormat("yyy-MM-dd").format(new Date()) + "\\" +
                new SimpleDateFormat("HH-mm-ss").format(new Date()) + "\\";
        return driver;
    }

    public TestSuite() throws FileNotFoundException {
        config = gson.fromJson(new FileReader("config.json"), Config.class);
    }

    @AfterMethod
    public void cleanup() {
        LOGGER.info("Running Cleanup Operations");
        driver.quit();
    }

    public void delay(int mSec) { try { Thread.sleep(mSec); } catch (Exception e) {
        LOGGER.debug("delay did not work");
        }
    }

    public void createResultFolder(String test) {
        LOGGER.info("Creating results folder for test: " + test);
        try {
            this.screenshotFolder = this.screenshotFolder + test + "\\";
            Files.createDirectories(Paths.get(this.screenshotFolder));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
