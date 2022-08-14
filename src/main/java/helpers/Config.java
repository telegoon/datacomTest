package helpers;

public class Config {

    String demoURL;
    String driverPath;
    String resultsDir;

    public Config(String demoURL, String driverPath, String resultsDir) {
        this.demoURL = demoURL;
        this.driverPath = driverPath;
        this.resultsDir = resultsDir;
    }

    public String getDemoURL() {
        return demoURL;
    }

    public void setDemoURL(String demoURL) {
        this.demoURL = demoURL;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getResultsDir() {
        return resultsDir;
    }

    public void setResultsDir(String resultsDir) {
        this.resultsDir = resultsDir;
    }
}
