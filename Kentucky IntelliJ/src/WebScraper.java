import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.sql.*;
import java.util.List;

public class WebScraper {

    static String unseparated, usdot, names, address, reason, date, status, newDate, legalName, dbName, street, city, state, zip,
            insertSQL, hostName, database, user, password;
    static int linelocation, percentlocation, hashtaglocation, nlocation, commalocation, lastspace, rowCounter = 0;
    static WebDriver driver;

    public static void main(String[] args) throws IOException, SQLException {
        Start();
        insertIntoDatabase();
    }

    public static void insertIntoDatabase() throws IOException, SQLException {
        File file = new File("C:\\Users\\cobyf\\Desktop\\Kentucky.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        unseparated = br.readLine();
        br.close();
        linelocation = unseparated.indexOf("|");
        percentlocation = unseparated.indexOf("%");
        hashtaglocation = unseparated.indexOf("#");
        hostName = unseparated.substring(0, linelocation);
        database = unseparated.substring(linelocation + 1, percentlocation);
        user = unseparated.substring(percentlocation + 1, hashtaglocation);
        password = unseparated.substring(hashtaglocation + 1);

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
            // System.out.println("Old Date: " + date + " | New Date: " + newDate);

            if(names.contains("\n")) {
                legalName = names.substring(0, names.indexOf("\n"));
                dbName = names.substring(names.indexOf("\n") + 1);
            } else {
                legalName = names;
                dbName = "";
            }
            if(names.contains("'") || address.contains("'")) {
                legalName = legalName.replaceAll("'", "");
                dbName = dbName.replaceAll("'", "");
                address = address.replaceAll("'", "");
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
                statement.executeUpdate(insertSQL);
                System.out.println(insertSQL + " was inserted.");
                rowCounter++;
            }
            catch (Exception ex) {
                System.out.println("Annoyance: " + ex);
                System.out.println(insertSQL + " was not inserted.");
            }
        }

        System.out.println("\n\nDatabase Insertions complete!\n" + rowCounter + " rows inserted.");
        connection.close();
    }

    public static void Start() {
        System.setProperty("webdriver.chrome.driver", "E:\\College\\!JAR Files\\Selenium Files\\ChromeDriver GamingLaptop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
    }
}