import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IntelliJSelenium {

    static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Coby\\Desktop\\UC Des & Tes\\Selenium\\IntelliJ Selenium\\chromedriver.exe");
        // System.out.println("Beginning Test");
        driver = new ChromeDriver();
        driver.get("https://walmart.com");
        System.out.println("The page title is: " + driver.getTitle());
        // System.out.println("Ending Test");
        gotMilk();
        driver.quit();
    }

    public static void gotMilk() {
        String newTitle;
        WebElement textbox = driver.findElement(By.id("global-search-input"));
        textbox.sendKeys("Milk");
        textbox.submit();
        newTitle = driver.getTitle();
        System.out.println("New Title is: " + newTitle);
        if (newTitle.startsWith("Milk - Walmart.com"))
            System.out.println("We got milk!");
        else
            System.out.println("Don't got milk..");
    }
}
