import com.sun.deploy.cache.Cache;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.List;
import java.util.Scanner;

public class WebScraper {

    static String usdot, names, address, reason, date, status, newDate, legalName, dbName, street, city, state, zip, insertSQL;
    static int nlocation, commalocation, lastspace, rowCounter = 0;
    static WebDriver driver;

    public static void main(String[] args) throws IOException, SQLException {
        Start();
        insertIntoDatabase();
    }

    public static void insertIntoDatabase() throws IOException, SQLException {
        String hostName = "ptcwork.database.windows.net";
        String database = "ChameleonCompanies";
        String user;
        String password;
        File file = new File("C:\\Users\\cobyf\\Desktop\\Kentucky.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String unseparated = br.readLine();
        br.close();
        user = unseparated.substring(0, unseparated.indexOf(","));
        password = unseparated.substring(unseparated.indexOf(",") + 1);
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, database, user, password);
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        driver.get("https://li-public.fmcsa.dot.gov/LIVIEW/pkg_oos_process.prc_list?pv_vpath=LIVIEW&" +
                "pv_show_all=N&pn_dotno=&pn_docket=&pv_legalname=&s_state=KYUS");
        List<WebElement> rows = driver.findElements(By.xpath("/html/body/font/table[2]/tbody/tr"));

        for(int x = 2; x <= rows.size(); x++) {
            usdot = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/th/center/font")).getText().trim();
            names = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[1]/center")).getText().trim();
            address = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[2]/center")).getText().trim();
            reason = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[3]/center/font")).getText().trim().toUpperCase();
            date = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[4]/center/font")).getText().trim();
            status = driver.findElement(By.xpath("/html/body/font/table[2]/tbody/tr[" + x + "]/td[5]/center/font")).getText().trim();

            String[] dateInfo = date.split("-");
            newDate = dateInfo[2] + "/" + dateInfo[0] + "/" + dateInfo[1];
            if(names.contains("\n")) {
                legalName = names.substring(0, names.indexOf("\n"));
                dbName = names.substring(names.indexOf("\n") + 1);
            } else {
                legalName = names;
                dbName = "";
            }
            // System.out.println("Legal Name: " + legalName + "\nDBA Name: " + dbName);
            nlocation = address.indexOf("\n");
            commalocation = address.indexOf(",");
            lastspace = address.lastIndexOf(" ");
            street = address.substring(0, nlocation);
            city = address.substring(nlocation + 1, commalocation - 1);
            state = address.substring(commalocation + 2, lastspace);
            zip = address.substring(lastspace + 1);
            // System.out.println("Street: " + street + "\nCity: " + city + "\nState: " + state + "\nZIP: " + zip + "\n");

            try {
                insertSQL = String.format("exec SP_Add_Inactive '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s';",
                        usdot, legalName, dbName, street, city, state, zip, reason, newDate, status);
                // System.out.println(insertSQL + " would be inserted.");
                statement.executeQuery(insertSQL);
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            rowCounter++;
        }
        System.out.println("\n\nDatabase Insertions complete!\n" + rowCounter + " rows inserted.");
    }

    public static void displayWebsiteInfo() {
        driver.get("https://li-public.fmcsa.dot.gov/LIVIEW/pkg_oos_process.prc_list?pv_vpath=LIVIEW&" +
                "pv_show_all=N&pn_dotno=&pn_docket=&pv_legalname=&s_state=KYUS");
        List<WebElement> rows = driver.findElements(By.xpath("/html/body/font/table[2]/tbody/tr"));
        for(int x = 2; x <= rows.size(); x++)
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
            System.out.println("OOS REASON: " + oosReason.getText().toUpperCase());
            System.out.println("OOS DATE: " + newDate);
            System.out.println("STATUS: " + status.getText() + "\n");
        }
    }

    public static void Start() {
        System.setProperty("webdriver.chrome.driver", "E:\\College\\!JAR Files\\Selenium Files\\ChromeDriver GamingLaptop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
    }
}