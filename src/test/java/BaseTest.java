import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/wojciechpotocki/Documents/szkolenia/testuj/bootcamp/driver/chromedriver");
        driver = new ChromeDriver();
        // maksymalizacja okna
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // do selenium 3
        // od selenium 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
