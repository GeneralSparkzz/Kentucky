import com.sun.deploy.cache.Cache;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JUnitTestsTest {

    static WebDriver driver;
    private static Cache FileUtils;

    /**
     * Completes the tests by taking a screenshot at the end and closing Google Chrome.
     * Screenshot only useful if test fails because headless version doesn't show error location.
     * Screenshot will show exact time that test fails and what the screen looks like.
     * @throws IOException
     */
    @AfterClass
    public static void End() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File
                ("D:\\College\\UC Des & Tes\\Selenium\\DeathScreen.jpg"));
        driver.quit();
    }

    /**
     * Begins the tests by finding chromedriver, setting it to headless mode, and opening Google Chrome.
     */
    @Before
    public void Start() {
        System.setProperty("webdriver.chrome.driver", "D:\\College\\~JAR Files\\Selenium Files\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("window-size=1920x1080");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Testing the first page of PHPTravels. Specifically selecting a tour in Paris, France.
     */
    @Ignore
    public void phptravelsFirstPage() {
        driver.get("https://www.phptravels.net/m-tours");
        PHPTravelsTours mainData = new PHPTravelsTours(driver);
        mainData.getDestinationBox().click();
        mainData.getDestinationBox().sendKeys("Paris");
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li[1]/div/span")));
        mainData.getDestinationBox().sendKeys(Keys.ENTER);
        // (Paris, France) selected as tour location.
        mainData.getTourTypeBox().click();
        new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tourtype_chosen\"]/div/ul/li[2]")));
        driver.findElement(By.xpath("//*[@id=\"tourtype_chosen\"]/div/ul/li[2]")).click();
        // (Private) tour type option selected.
        mainData.getDateBox().click();
        new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[6]/div/div/div[2]/div[20]")));
        driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[6]/div/div/div[2]/div[20]")).click();
        // (17th of January) selected for date.
        driver.findElement(By.xpath("//*[@id=\"tours\"]/div/div/form/div/div/div[3]/div/div/div/div/" +
                "div/div/div[2]/div/div[2]/div/span/button[1]")).click();
        // (2) adults selected.
        mainData.getSearchButton().click();
    }

    /**
     * Testing the second page of PHPTravels, changing the date and number of participants in the tour.
     */
    @Ignore
    public void phptravelsSecondPage() {
        driver.get("https://www.phptravels.net/tours/search?txtSearch=&type=187&checkin=17%2F01%2F2020" +
                "&adults=2&module_type=location&slug=france%2Fparis&searching=&modType=");
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/section/div/div[2]/div[2]" +
                "/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[2]/a")).click();
        // (Spectaculars of the Nile 3 Nights) selected.
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[4]/div/div[1]" +
                "/aside/div/form/div/div/div/div/div[1]/input")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div[2]/div[20]")));
        driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div[2]/div[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[4]/div/div[1]" +
                "/aside/div/form/div/div/div/div/div[2]/button")).click();
        // Date changed to (17th of January)
        driver.findElement(By.xpath("//*[@id=\"selectedAdults\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"selectedAdults\"]/option[2]")).click();
        // (2 Adults) selected.
        js.executeScript("window.scrollBy(0,400)");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[4]/div/div[1]/aside/div/form/div/form/button")).click();
        // (Book Now) button clicked.
    }

    /**
     * Testing the checkout page of PHPTravels where I finish booking the Tour.
     */
    @Ignore
    public void phptravelsThirdPage() {
        driver.get("https://www.phptravels.net/tours/book/Spectaculars-Of-The-Nile-3-Nights?" +
                "date=17%2F01%2F2020&adults=2&child=0&infant=0");
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[1]/div[1]/div/input")).sendKeys("Coby");
        // (Coby) inputted as First name.
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[1]/div[2]/input")).sendKeys("Frye");
        // (Frye) inputted as Last name.
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[2]/div[1]/input")).sendKeys("coby@frye.com");
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[2]/div[2]/input")).sendKeys("coby@frye.com");
        // (coby@frye.com) inputted as Email.
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[3]/div/input")).sendKeys("234567890");
        // (234567890) inputted as Phone Number.
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[4]/div/input")).sendKeys("1234 Fairytale Rd");
        // (1234 Fairytale Rd) inputted as Address.
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[5]/div/div[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[5]/div/div[2]/div/ul/li[2]")).click();
        // (Albania) selected as Country.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[9]/button")).click();
        // Scroll down, clicked (Confirm This Booking).
    }

    /**
     * Confirming final page of PHPTravels to ensure that Tour is booked and ready to go!
     */
    @Ignore
    public void phptravelsFourthPage() {
        driver.get("https://www.phptravels.net/invoice?id=84&sessid=9081");
        // (Time limited, must be replaced often).
//        driver.findElement(By.xpath("//*[@id=\"84\"]")).click();
//        driver.switchTo().alert().accept();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,200)");
        // (Click Pay on Arrival) (Needs Reserved Only Once).
        String status = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/h4")).getText();
        assertEquals("Your booking status is Reserved", status);
    }

    @Ignore
    public void previousTests() {
//        @Ignore
//        public void thePOMTest () {
//            driver.get("https://www.cdc.gov/healthyweight/assessing/bmi/adult_bmi/english_bmi_calculator/bmi_calculator.html");
//            CDC_English testData = new CDC_English(driver);
//            testData.setPounds("200");
//            testData.setFeet("5");
//            testData.setInches("10");
//            testData.getCalc().click();
//            // Verify Results
//            CDC_Results theResults = new CDC_Results(driver);
//            // Could Assert here. Output for simplicity.
//            System.out.println(theResults.getBmiNum().getAttribute("innerHTML") + " BMI.");
//        }
//
//        // Selenium UCD Pizza Tests
//
//        /**
//         * Testing for Order Summary Completion
//         * Sets up Chrome, sets default values to "Supreme", and instantiates all objects on screen.
//         * Then select objects, calculates the order summary, and then tests it against what it is supposed to be.
//         */
//        @Ignore
//        public void testOrderSummary () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            String recipeName = "Veggie Lovers", toppings = "", size = "", fullOrder;
//            Select recipeSelector = new Select(driver.findElement(By.id("recipe")));
//            recipeSelector.selectByVisibleText(recipeName);
//            WebElement[] testItems = new WebElement[12];
//            String[] foodNames = {"pepperoni", "sausage", "ham", "bacon", "greenpepper",
//                    "mushroom", "tomato", "pineapple", "xcheese", "sizeSmall", "sizeMedium", "sizeLarge"};
//            for (int x = 0; x < foodNames.length; x++)
//                testItems[x] = driver.findElement(By.id(foodNames[x]));
//            for (int x = 0; x < 9; x++) {
//                if (testItems[x].isSelected())
//                    toppings += foodNames[x] + " ";
//            }
//            if (testItems[9].isSelected())
//                size = "Small";
//            else if (testItems[10].isSelected())
//                size = "Medium";
//            else if (testItems[11].isSelected())
//                size = "Large";
//            fullOrder = "You ordered a " + size + " " + recipeName + " pizza with " + toppings + "pizza.";
//            assertEquals("Checking Supreme",
//                    "You ordered a Large Veggie Lovers pizza with mushroom tomato pineapple pizza.",
//                    fullOrder);
//        }
//
//        /**
//         * Testing for "Build Your Own" completion.
//         * Begins with opening Chrome, selecting "Build Your Own" to clear all options, and
//         * tests expected output against what it is supposed to be.
//         */
//        @Ignore
//        public void testBuildYourOwn () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement dropdownBox = driver.findElement(By.id("recipe"));
//            Select recipe = new Select(dropdownBox);
//            recipe.selectByVisibleText("Build Your Own");
//            WebElement[] testItems = new WebElement[9];
//            String[] foodNames = {"pepperoni", "sausage", "ham", "bacon", "greenpepper",
//                    "mushroom", "tomato", "pineapple", "xcheese"};
//            for (int x = 0; x < foodNames.length; x++)
//                testItems[x] = driver.findElement(By.id(foodNames[x]));
//            for (int x = 0; x < testItems.length; x++)
//                assertTrue(testItems[x].toString(), !testItems[x].isSelected());
//        }
//
//        /**
//         * Testing for "Veggie Lovers" completion.
//         * Begins with opening Chrome, selecting "Veggie Lovers" to clear all options, and
//         * tests expected output against what it is supposed to be.
//         */
//        @Ignore
//        public void testVeggieLovers () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement dropdownBox = driver.findElement(By.id("recipe"));
//            Select recipe = new Select(dropdownBox);
//            recipe.selectByVisibleText("Veggie Lovers");
//            WebElement[] testItems = new WebElement[4];
//            String[] foodNames = {"greenpepper", "mushroom", "tomato", "pineapple"};
//            for (int x = 0; x < foodNames.length; x++)
//                testItems[x] = driver.findElement(By.id(foodNames[x]));
//            for (int x = 0; x < testItems.length; x++)
//                assertTrue(testItems[x].toString(), testItems[x].isSelected());
//        }
//
//        /**
//         * Testing for "Supreme" completion.
//         * Begins with opening Chrome, selecting "Supreme" to clear all options, and
//         * tests expected output against what it is supposed to be.
//         */
//        @Ignore
//        public void testSupreme () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement dropdownBox = driver.findElement(By.id("recipe"));
//            Select recipe = new Select(dropdownBox);
//            recipe.selectByVisibleText("Supreme");
//            WebElement[] testItems = new WebElement[6];
//            String[] foodNames = {"pepperoni", "sausage", "greenpepper", "mushroom", "tomato", "xcheese"};
//            for (int x = 0; x < foodNames.length; x++)
//                testItems[x] = driver.findElement(By.id(foodNames[x]));
//            for (int x = 0; x < testItems.length; x++)
//                assertTrue(testItems[x].toString(), testItems[x].isSelected());
//        }
//
//        /**
//         * Testing for "Meat Lovers" completion.
//         * Begins with opening Chrome, selecting "Meat Lovers" to clear all options, and
//         * tests expected output against what it is supposed to be.
//         */
//        @Ignore
//        public void testMeatLovers () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement dropdownBox = driver.findElement(By.id("recipe"));
//            Select recipe = new Select(dropdownBox);
//            WebElement pepperoni = driver.findElement(By.id("pepperoni"));
//            WebElement sausage = driver.findElement(By.id("sausage"));
//            WebElement ham = driver.findElement(By.id("ham"));
//            WebElement bacon = driver.findElement(By.id("bacon"));
//            recipe.selectByVisibleText("Meat Lovers");
//            assertTrue("Checking Pepperoni", pepperoni.isSelected());
//            assertTrue("Checking Sausage", sausage.isSelected());
//            assertTrue("Checking Ham", ham.isSelected());
//            assertTrue("Checking Bacon", bacon.isSelected());
//        }
//
//        /**
//         * Tests the Price of the Large no topping pizza.
//         * Selects a size and calculates and checks the cost of a Large pizza with no toppings.
//         */
//        @Ignore
//        public void testLargeNormalPrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeLarge"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement normalPrice = driver.findElement(By.id("price"));
//            smallOption.click();
//            orderButton.click();
//            assertEquals("Checking Large Pizza Price", "Price $15.00", normalPrice.getText());
//        }
//
//        /**
//         * Tests the Price of the Medium no topping pizza.
//         * Selects a size and calculates and checks the cost of a Medium pizza with no toppings.
//         */
//        @Ignore
//        public void testMediumNormalPrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeMedium"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement normalPrice = driver.findElement(By.id("price"));
//            smallOption.click();
//            orderButton.click();
//            assertEquals("Checking Medium Pizza Price", "Price $12.00", normalPrice.getText());
//        }
//
//        /**
//         * Tests the Price of the Small no topping pizza.
//         * Selects a size and calculates and checks the cost of a Small pizza with no toppings.
//         */
//        @Ignore
//        public void testSmallNormalPrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeSmall"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement normalPrice = driver.findElement(By.id("price"));
//            smallOption.click();
//            orderButton.click();
//            assertEquals("Checking Small Pizza Price", "Price $9.00", normalPrice.getText());
//        }
//
//        /**
//         * Test the Price of a Large pizza with 2 toppings.
//         * Selects a size and calculates and checks the cost of a Large pizza with 2 toppings.
//         */
//        @Ignore
//        public void testLargePrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeLarge"));
//            WebElement sausageOption = driver.findElement(By.id("sausage"));
//            WebElement baconOption = driver.findElement(By.id("bacon"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement totalPrice = driver.findElement(By.id("totPrice"));
//            smallOption.click();
//            sausageOption.click();
//            baconOption.click();
//            orderButton.click();
//            assertEquals("Checking Large 2 Topping Price", "Total Price $20.33", totalPrice.getText());
//        }
//
//        /**
//         * Test the Price of a Medium pizza with 2 toppings.
//         * Selects a size and calculates and checks the cost of a Medium pizza with 2 toppings.
//         */
//        @Ignore
//        public void testMediumPrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeMedium"));
//            WebElement sausageOption = driver.findElement(By.id("sausage"));
//            WebElement baconOption = driver.findElement(By.id("bacon"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement totalPrice = driver.findElement(By.id("totPrice"));
//            smallOption.click();
//            sausageOption.click();
//            baconOption.click();
//            orderButton.click();
//            assertEquals("Checking Medium 2 Topping Price", "Total Price $16.05", totalPrice.getText());
//        }
//
//        /**
//         * Test the Price of a Small pizza with 2 toppings.
//         * Selects a size and calculates and checks the cost of a Small pizza with 2 toppings.
//         */
//        @Ignore
//        public void testSmallPrice () {
//            driver.get("http://leecottrell.com/ptc/ucdpizza.html");
//            WebElement smallOption = driver.findElement(By.id("sizeSmall"));
//            WebElement sausageOption = driver.findElement(By.id("sausage"));
//            WebElement baconOption = driver.findElement(By.id("bacon"));
//            WebElement orderButton = driver.findElement(By.className("btn"));
//            WebElement totalPrice = driver.findElement(By.id("totPrice"));
//            smallOption.click();
//            sausageOption.click();
//            baconOption.click();
//            orderButton.click();
//            assertEquals("Checking Small 2 Topping Price", "Total Price $11.77", totalPrice.getText());
//        }
//
//        // Notes and More
//
//        @Ignore
//        public void testScreenshot () {
//            driver.get("http://www.ptcollege.edu");
//            driver.manage().window().fullscreen();
//        }
//
//        @Ignore
//        public void testPopup () throws Exception {
//            driver.get("http://demo.guru99.com/test/detel_customer.php");
//            driver.findElement(By.name("cusid")).sendKeys("123");
//            driver.findElement(By.name("submit")).submit();
//            // Popup Appears
//            WebDriverWait wait = new WebDriverWait(driver, 10); // 10 is max wait.
//            wait.until(ExpectedConditions.alertIsPresent());
//            Alert alert = driver.switchTo().alert(); // Swap to the popup.
//            // Do something with the alert.
//            alert.accept(); // Clicks "Yes" button.
//        }
//
//        @Ignore
//        public void testChildWindow () {
//            driver.get("http://demo.guru99.com//popup.php");
//            // Get Handle of Parent window.
//            String parentWindowID = driver.getWindowHandle();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int x = 0; x <= 5; x++) {
//                driver.findElement(By.linkText("Click Here")).click();
//                driver.switchTo().window(parentWindowID);
//            }
//
//        }
//
//        @Ignore
//        public void checkPie () {
//            driver.get("https://caloriecontrol.org/healthy-weight-tool-kit/food-calorie-calculator/");
//            WebElement textBox, button, name, calories;
//            textBox = driver.findElement(By.id("keyword"));
//            button = driver.findElement(By.id("btnsearch"));
//            textBox.sendKeys("PIE,PUMPKIN,PREP FROM RECIPE");
//            button.submit();
//            WebElement tbody = driver.findElement(By.id("foodtbody"));
//            System.out.println("Table:" + tbody);
//            List<WebElement> dataList = null;
//            WebElement tr = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/table/tbody/tr"));
//            System.out.println("TR: " + tr);
//            dataList = tr.findElements(By.tagName("td"));
//            System.out.println("dataList: " + dataList);
//            for (WebElement data : dataList) {
//                System.out.println("Data: " + data);
//            }
//        }
//
//        @Ignore
//        public void testColors () {
//            String dataName, cssValue;
//            driver.get("https://leecottrell.com/ptc/colors.html");
//            WebElement shadesTable = driver.findElement(By.id("grayTable"));
//            List<WebElement> tableRows = shadesTable.findElements(By.tagName("tr"));
//            for (WebElement row : tableRows) {
//                List<WebElement> tableData = row.findElements(By.tagName("td"));
//                for (WebElement data : tableData) {
//                    dataName = data.getText();
//                    dataName = dataName.replace("rgb", "rgba");
//                    dataName = dataName.replace(")", ",1)");
//                    dataName = dataName.replaceAll(" ", "");
//                    cssValue = data.getCssValue("background-color");
//                    cssValue = cssValue.replaceAll(" ", "");
//                    assertEquals("Checking <td> with CSS value of: " + data.getCssValue("background-color"),
//                            dataName,
//                            cssValue);
//                }
//            }
//        }
//
//        @Ignore
//        public void testDarkerColors () {
//            String dataName, cssValue;
//            driver.get("https://leecottrell.com/ptc/colors.html");
//            WebElement shadesTable2 = driver.findElement(By.id("darkerTable"));
//            List<WebElement> tableRows2 = shadesTable2.findElements(By.tagName("tr"));
//            for (WebElement row : tableRows2) {
//                List<WebElement> tableData = row.findElements(By.tagName("td"));
//                for (WebElement data : tableData) {
//                    dataName = data.getText();
//                    dataName = dataName.replace("rgb", "rgba");
//                    dataName = dataName.replace(")", ",1)");
//                    dataName = dataName.replaceAll(" ", "");
//                    cssValue = data.getCssValue("background-color");
//                    cssValue = cssValue.replaceAll(" ", "");
//                    assertEquals("Checking <td> of Darker Table with CSS value of: " +
//                                    data.getCssValue("background-color"),
//                            dataName,
//                            cssValue);
//                }
//            }
//        }
//
//        @Ignore
//        public void testStuWeb () {
//            // 1.0
//            driver = new ChromeDriver();
//            driver.get("http://cottrell.lee.stuweb.ptcollege.edu/carpetPostPersistent.php");
//            // 1.1
//            WebElement nameBox, lengthBox, widthBox, costBox, calcButton;
//            nameBox = driver.findElement(By.id("roomName"));
//            lengthBox = driver.findElement(By.id("rlength"));
//            widthBox = driver.findElement(By.id("rwidth"));
//            costBox = driver.findElement(By.id("carpetCost"));
//            calcButton = driver.findElement(By.xpath("/html/body/div/form/button[1]"));
//            // 1.2
//            nameBox.sendKeys("Room 602");
//            widthBox.sendKeys("45");
//            lengthBox.sendKeys("60");
//            costBox.sendKeys("4.50");
//            // 1.3
//            calcButton.click();
//            // 1.4
//            String div = driver.findElement(By.xpath("/html/body/div")).getText();
//            assertTrue(div, div.contains("13,000.50")); // Passes
//            driver.close();
//            // 1.5
//            assertTrue(div, div.contains("19,450.00")); // Dies
//        }
//
//        @Ignore
//        public void testPTCollege1 () {
//            // 2.0
//            driver = new ChromeDriver();
//            driver.get("https://www.ptcollege.edu/");
//            // 2.1
//            WebElement link = driver.findElement(By.linkText("Student Logins"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
//            link.click();
//            WebElement title = driver.findElement(By.className("entry-title"));
//            assertEquals("Current Student Logins", title.getText());
//        }
//
//        @Ignore
//        public void testPTCollege2 () {
//            // 2.0
//            driver = new ChromeDriver();
//            driver.get("https://www.ptcollege.edu/");
//            // 2.2
//            List<WebElement> linkList = driver.findElements(By.xpath("//a"));
//            // assertEquals("Campus Alert System", linkList.get(5).getText());
//            // Confirmed By Cottrell (10:47, 12/4/2019) to just move on.
//            // 2.3
//            assertEquals("300", linkList.size());
//        }
    }
}