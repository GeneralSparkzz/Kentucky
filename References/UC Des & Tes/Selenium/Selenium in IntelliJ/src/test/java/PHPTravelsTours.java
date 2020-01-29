import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PHPTravelsTours {

    // @FindBy(xpath = "/html/body/div[6]/div/input")
    @FindBy(linkText = "Search by Listing or City Name")
    WebElement destinationBox;
    @FindBy(id = "tourtype_chosen")
    WebElement tourTypeBox;
    @FindBy(id = "DateTours")
    WebElement dateBox;
    @FindBy(name = "adults")
    WebElement adultBox;
    @FindBy(xpath = "//*[@id=\"tours\"]/div/div/form/div/div/div[4]/button")
    WebElement searchButton;

    WebDriver driver;

    public PHPTravelsTours(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PHPTravelsTours(WebElement destinationBox, WebElement tourTypeBox, WebElement dateBox,
                           WebElement adultBox, WebElement searchButton) {
        this.destinationBox = destinationBox;
        this.tourTypeBox = tourTypeBox;
        this.dateBox = dateBox;
        this.adultBox = adultBox;
        this.searchButton = searchButton;
    }

    public void setDestinationBox(String keys) {
        this.destinationBox.sendKeys(keys);
    }

    public String getDestinationBoxText() {
        return this.destinationBox.getText();
    }

    public void setTourType(String keys) {
        this.tourTypeBox.sendKeys(keys);
    }

    public String getTourTypeText() {
        return this.tourTypeBox.getText();
    }

    public void setDateBox(String keys) {
        this.dateBox.sendKeys(keys);
    }

    public String getDateBoxText() {
        return this.dateBox.getText();
    }

    public void setAdultBox(String keys) {
        this.adultBox.sendKeys(keys);
    }

    public String getAdultBoxText() {
        return this.adultBox.getText();
    }

    public WebElement getDestinationBox() {
        return destinationBox;
    }

    public void setDestinationBox(WebElement destinationBox) {
        this.destinationBox = destinationBox;
    }

    public WebElement getTourTypeBox() {
        return tourTypeBox;
    }

    public void setTourTypeBox(WebElement tourTypeBox) {
        this.tourTypeBox = tourTypeBox;
    }

    public WebElement getDateBox() {
        return dateBox;
    }

    public void setDateBox(WebElement dateBox) {
        this.dateBox = dateBox;
    }

    public WebElement getAdultBox() {
        return adultBox;
    }

    public void setAdultBox(WebElement adultBox) {
        this.adultBox = adultBox;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(WebElement searchButton) {
        this.searchButton = searchButton;
    }
}
