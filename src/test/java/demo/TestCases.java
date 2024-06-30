package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    private ChromeDriver driver;
    private Wrappers wrapperMethod;
    private WebDriverWait wait; 



    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     @Test
     public void testCase01() throws InterruptedException{
        System.out.println("Start TestCase01 : Navigating to URL");
        String URL = "https://www.flipkart.com/";
        wrapperMethod.PopularityCount(URL);
        System.out.println("End of testCase01");
     }

     @Test
     public void testCase02() throws InterruptedException{ 
        // String URL = "https://www.flipkart.com/";
        System.out.println("Starting testCase02: Search \"iPhone\", print the Titles and discount % of items with more than 17% discount");
        wrapperMethod.Titlesofitemsmorethan17discount();
        System.out.println("End of testCase02");
     }
     @Test
     public void testCase03() throws InterruptedException{
        String URL = "https://www.flipkart.com/";
        System.out.println("Starting testCase03: Search \"Coffee Mug\", select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews"); 
        wrapperMethod.TitlesAndImageUrlsOfTopFiveItems();
        System.out.println("End of testCase03");
     }
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
        driver = new ChromeDriver(options);
        wrapperMethod = new Wrappers(driver);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}