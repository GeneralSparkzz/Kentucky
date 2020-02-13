import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebScraper {

    static String unseparated, key, usdot, names, address, reason, date, status, newDate, legalName, dbName, street, city, state, zip,
            insertSQL, hostName, database, user, password, apiAddress, apiResponse, coordinates, newCity;
    static int linelocation, percentlocation, hashtaglocation, nlocation, commalocation, lastspace, rowCounter = 0;
    static double lat, lng;
    static File dbInfoFile, keyFile;
    static BufferedReader br;
    static URL url;
    static WebDriver driver;
    static ObjectMapper mapper = new ObjectMapper();
    static Map<String, Object> tempMap = new HashMap<>();

    public static void main(String[] args) throws IOException, SQLException {
        Start();
        PullLocalInfo();
        insertIntoDatabase();
    }

    public static void insertIntoDatabase() throws SQLException, IOException {
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, database, user, password);
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        driver.get("https://li-public.fmcsa.dot.gov/LIVIEW/pkg_oos_process.prc_list?pv_vpath=LIVIEW&" +
                "pv_show_all=N&pn_dotno=&pn_docket=&pv_legalname=&s_state=KYUS");
        List<WebElement> rows = driver.findElements(By.xpath("/html/body/font/table[2]/tbody/tr"));

        // for(int x = 2; x <= rows.size(); x++) {
        for(int x = 2; x <= 11; x++) {
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

            getGeocodedLocation();

            try {
                insertSQL = String.format("exec SP_Add_Inactive '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s';",
                        usdot, legalName, dbName, street, city, state, zip, reason, newDate, status, lat, lng);
                statement.executeUpdate(insertSQL);
                System.out.println(insertSQL.substring(insertSQL.indexOf("'")) + " was inserted.");
                System.out.println("Lat: " + lat + "\nLong: " + lng);
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

    public static void getGeocodedLocation() throws IOException {
        apiAddress = address.replaceAll(" ", "+").replaceAll("'", "");
        apiAddress = apiAddress.substring(0, apiAddress.indexOf("\n"));
        newCity = "+" + city.replaceAll(" ", "+");
        url = new URL(String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s,%s,+%s&key=%s",
                apiAddress, newCity, state, key));
        // System.out.println("Google API Link: " + url);
        tempMap = mapper.readValue(url, Map.class);
        apiResponse = tempMap.toString();
        // System.out.println(apiResponse);
        coordinates = apiResponse.substring(apiResponse.indexOf("location={") + 10, apiResponse.indexOf("}, location_type"));
        // System.out.println(coordinates);
        lat = Double.parseDouble(coordinates.substring(coordinates.indexOf("=") + 1, coordinates.indexOf(",")));
        lng = Double.parseDouble(coordinates.substring(coordinates.lastIndexOf("=") + 1));
    }

    public static void PullLocalInfo() throws IOException {
        dbInfoFile = new File("C:\\Users\\cobyf\\Desktop\\Secret Files\\Kentucky.txt");
        keyFile = new File("C:\\Users\\cobyf\\Desktop\\Secret Files\\API Key.txt");
        br = new BufferedReader(new FileReader(dbInfoFile));
        unseparated = br.readLine();
        linelocation = unseparated.indexOf("|");
        percentlocation = unseparated.indexOf("%");
        hashtaglocation = unseparated.indexOf("#");
        hostName = unseparated.substring(0, linelocation);
        database = unseparated.substring(linelocation + 1, percentlocation);
        user = unseparated.substring(percentlocation + 1, hashtaglocation);
        password = unseparated.substring(hashtaglocation + 1);
        br = new BufferedReader(new FileReader(keyFile));
        key = br.readLine();
        br.close();
    }

    public static void Start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\cobyf\\Desktop\\Secret Files\\cdriver78.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
    }
}