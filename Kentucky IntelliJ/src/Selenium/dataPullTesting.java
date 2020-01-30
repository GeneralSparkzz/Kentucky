package Selenium;

import com.sun.deploy.cache.Cache;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

public class dataPullTesting {

    static WebDriver driver;

    public static void main(String[] args) throws IOException {
        Start();
        displayWebsiteInfo();
        // End();
    }

    public static void displayWebsiteInfo() {
        driver.get("https://li-public.fmcsa.dot.gov/LIVIEW/pkg_oos_process.prc_list?pv_vpath=LIVIEW&" +
                "pv_show_all=N&pn_dotno=&pn_docket=&pv_legalname=&s_state=KYUS");
        System.out.println("Title: " + driver.getTitle() + "\n");
        for(int x = 2; x <= 10; x++)
        {
            WebElement usdot = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/th/center/font"));
            WebElement names = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[1]/center"));
            WebElement address = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[2]/center"));
            WebElement oosReason = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[3]/center/font"));
            WebElement oosDate = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[4]/center/font"));
            WebElement status = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[5]/center/font"));

            String newNames = names.getText().trim().replace("\n", ", ");

            String newAddress = address.getText().trim();
            newAddress = newAddress.replace("\n", ", "
                                  ).replace(" ,", ","
                                  ).replace("KY", "KY,");

            String newDate = oosDate.getText().trim();
            String[] dateInfo = newDate.split("-");
            newDate = dateInfo[2] + "-" + dateInfo[0] + "-" + dateInfo[1];

            System.out.println("USDOT#: " + usdot.getText().trim());
            System.out.println("LEGAL NAME, DBA NAME: " + newNames);
            System.out.println("ADDRESS: " + newAddress);
            System.out.println("OOS REASON: " + oosReason.getText());
            System.out.println("OOS DATE: " + newDate);
            System.out.println("STATUS: " + status.getText() + "\n");
        }
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
