import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CDC_Results {

    @FindBy(className = "bmiNum")
    private WebElement bmiNum;

    private WebDriver driver;

    public CDC_Results(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getBmiNum() {
        return bmiNum;
    }
}
