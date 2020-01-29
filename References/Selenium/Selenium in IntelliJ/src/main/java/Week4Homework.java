import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Week4Homework {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Coby\\Desktop\\UC Des & Tes\\Selenium\\IntelliJ Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.leecottrell.com/phpclass/funny.html");
        WebElement first = driver.findElement(By.name("FirstName"));
        WebElement last = driver.findElement(By.name("LastName"));
        WebElement age = driver.findElement(By.name("Age"));
        WebElement gender = driver.findElement(By.xpath("/html/body/form/fieldset/p[4]/input[2]"));
        WebElement submitBtn = driver.findElement(By.xpath("/html/body/form/fieldset/input[1]"));
        first.sendKeys("Coby");
        last.sendKeys("Frye");
        age.sendKeys("19");
        gender.click();
        submitBtn.click();
        driver.quit();
    }
}
