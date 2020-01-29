package Selenium;

import com.sun.deploy.cache.Cache;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class dataPullTesting {

    static WebDriver driver;

    public static void main(String[] args) throws IOException {
        Start();
        System.out.println("Developing Enterprise Applications - Kentucky Team");
        // End();
    }

    /**
     * Ends the program by taking a screenshot of the last second the driver was running with.
     * (Especially useful with testing code when using the headless driver version)
     *
     * @throws IOException
     */
    public static void End() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // BE SURE TO CHANGE FILE PATH OF SCREENSHOT BEFORE RUNNING
        Cache.copyFile(screenshot, new File
                ("C:\\Users\\cobyf\\Desktop\\Kentucky\\Kentucky IntelliJ\\Other Docs"));
        driver.quit();
    }

    /**
     * Begins the program by creating a new Headless Chrome driver.
     */
    public static void Start() {
        System.setProperty("webdriver.chrome.driver", "D:\\College\\~JAR Files\\Selenium Files\\chromedriver.exe");
        // THESE OPTIONS MAKE IT HEADLESS
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        // TO REMOVE HEADLESS, COMMENT OUT THE ABOVE LINES AND UNCOMMENT THE FOLLOWING 2 LINES OF CODE
        // driver = new ChromeDriver();
        // driver.manage().window().maximize();
    }
}
