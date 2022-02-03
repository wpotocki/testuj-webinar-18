package sampleshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import sampleshop.utils.ScreenshotUtil;

import java.text.SimpleDateFormat;
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

    @AfterMethod
    public void testTearDown(ITestResult result) {
        if (!result.isSuccess()) {
            System.out.println("Metoda testowa " + result.getMethod().getMethodName() + " zakończona niepowodzeniem");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
            String readableTimestamp = formatter.format(result.getEndMillis());

            String screenshotPath = "./target/artifacts/screenshots/"+ result.getMethod().getMethodName() + "_" + readableTimestamp + ".png";
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
