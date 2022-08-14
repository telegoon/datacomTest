package pages;

import helpers.MyWaits;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Common {
    final Logger LOGGER = LoggerFactory.getLogger(Common.class);
    String screenshotFolder;
    MyWaits waits = new MyWaits();
    WebDriver driver;

    public Common(WebDriver driver) {
        this.driver = driver;
    }

    public void setScreenshotFolder(String folder) { this.screenshotFolder = folder; }

    protected void takeScreenShot(WebDriver driver, String saveName) {
        LOGGER.info(String.format("Taking screenshot for - %s", saveName));
        try {
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            String output = String.format("%s%s.png", this.screenshotFolder, saveName);
            ImageIO.write(screenShot, "PNG", new File(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
