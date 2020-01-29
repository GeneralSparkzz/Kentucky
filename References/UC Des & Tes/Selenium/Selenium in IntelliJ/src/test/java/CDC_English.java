import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CDC_English {

    // Build Objects for each page element.
    @FindBy(id="pounds")
    WebElement pounds;
    @FindBy(id="feet")
    WebElement feet;
    @FindBy(id="inches")
    WebElement inches;
    // @FindBy(xpath="//*[@id=\"calc\"\")
    @FindBy(id="calc")
    WebElement calc;

    WebDriver driver;

    public CDC_English() {

    }

    public CDC_English(WebDriver driver) {
        this.driver = driver;
        // Instantiate all other objects.
        PageFactory.initElements(driver, this);
    }

    public WebElement getPounds() {
        return pounds;
    }

    // Optional
    public String getPoundsText() {
        return pounds.getText();
    }

    public void setPounds(WebElement pounds) {
        this.pounds = pounds;
    }

    // My Custom Setter
    public void setPounds(String lbs) {
        this.pounds.sendKeys(lbs);
    }

    public WebElement getFeet() {
        return feet;
    }

    public void setFeet(WebElement feet) {
        this.feet = feet;
    }

    public void setFeet(String ft) {
        this.feet.sendKeys(ft);
    }

    public WebElement getInches() {
        return inches;
    }

    public void setInches(WebElement inches) {
        this.inches = inches;
    }

    public void setInches(String in) {
        this.inches.sendKeys(in);
    }

    public WebElement getCalc() {
        return calc;
    }

    public void setCalc(WebElement calc) {
        this.calc = calc;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
