package sampleshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // wyłączenie logowania zbyt dużej informacji przy uruchamianiu drivera
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty("webdriver.chrome.driver", "/Users/wojciechpotocki/Documents/szkolenia/testuj/bootcamp/driver/chromedriver");
        driver = new ChromeDriver();
        // maksymalizacja okna
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://sampleshop.inqa.pl/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
