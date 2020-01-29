import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PHPTravelsMain {

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[1]/div/div/form/div/div/div[1]/div/div[2]/input")
    WebElement destinationBox;
    @FindBy(name = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[1]/div/div/form/div/div/div[2]/div/div/div[1]/div/div[2]/input")
    WebElement checkinBox;
    @FindBy(name = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[1]/div/div/form/div/div/div[2]/div/div/div[2]/div/div[2]/input")
    WebElement checkoutBox;
    @FindBy(name = "adults")
    WebElement adultBox;
    @FindBy(name = "children")
    WebElement childrenBox;
    @FindBy(className = "btn btn-primary btn-block")
    WebElement searchButton;

    WebDriver driver;

    public PHPTravelsMain() {
        // Default Constructor
    }

    public PHPTravelsMain(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getDestinationBox() {
        return destinationBox;
    }

    // Destination Box Get Text
    public String getDestinationBoxText() {
        return this.destinationBox.getText();
    }

    public void setDestinationBox(WebElement destinationBox) {
        this.destinationBox = destinationBox;
    }

    // Destination Box Send Keys
    public void setDestinationBox(String keys) {
        this.destinationBox.sendKeys(keys);
    }

    public WebElement getCheckinBox() {
        return checkinBox;
    }

    // Checkin Box Get Text
    public String getCheckinBoxText() {
        return this.checkinBox.getText();
    }

    public void setCheckinBox(WebElement checkinBox) {
        this.checkinBox = checkinBox;
    }

    // Checkin Box Send Keys
    public void setCheckinBox(String keys) {
        this.checkinBox.sendKeys(keys);
    }

    public WebElement getCheckoutBox() {
        return checkoutBox;
    }

    // Checkout Box Get Text
    public String getCheckoutBoxText() {
        return this.checkoutBox.getText();
    }

    public void setCheckoutBox(WebElement checkoutBox) {
        this.checkoutBox = checkoutBox;
    }

    // Checkout Box Send Keys
    public void setCheckoutBox(String keys) {
        this.checkoutBox.sendKeys(keys);
    }

    public WebElement getAdultBox() {
        return adultBox;
    }

    // Adult Box Get Text
    public String getAdultBoxText() {
        return this.adultBox.getText();
    }

    public void setAdultBox(WebElement adultBox) {
        this.adultBox = adultBox;
    }

    // Adult Box Send Keys
    public void setAdultBox(String keys) {
        this.adultBox.sendKeys(keys);
    }

    public WebElement getChildrenBox() {
        return childrenBox;
    }

    // Children Box Get Text
    public String getChildrenBoxText() {
        return this.childrenBox.getText();
    }

    public void setChildrenBox(WebElement childrenBox) {
        this.childrenBox = childrenBox;
    }

    // Children Box Send Keys
    public void setChildrenBox(String keys) {
        this.childrenBox.sendKeys(keys);
    }

    // Click Search Button
    public void clickSearchButton() {
        this.searchButton.click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
