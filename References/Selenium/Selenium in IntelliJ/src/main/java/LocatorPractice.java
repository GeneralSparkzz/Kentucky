import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorPractice {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Coby\\Desktop\\UC Des & Tes\\Selenium\\IntelliJ Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        // stuweb();
        // ptcollege();
        driver.quit();
    }

    public static void stuweb() {
        driver.get("http://cottrell.lee.stuweb.ptcollege.edu/carpetPostPersistent.php");
        WebElement roomBox = driver.findElement(By.id("roomName"));
        WebElement lengthBox = driver.findElement(By.id("rlength"));
        WebElement widthBox = driver.findElement(By.id("rwidth"));
        List<WebElement> buttonList = driver.findElements(By.className("btn"));
        List<WebElement> buttonXPathList = driver.findElements(By.xpath("//button"));
        System.out.println(buttonList);
        System.out.println(buttonXPathList);
    }

    public static void ptcollege() {
        driver.get("https://www.ptcollege.edu/");
        WebElement link5 = driver.findElement(By.xpath("(//a)[5]"));
        System.out.println(link5);
    }
}
