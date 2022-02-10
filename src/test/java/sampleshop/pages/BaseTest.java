package sampleshop.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import sampleshop.PropertiesConfig;
import sampleshop.utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;

import static io.qameta.allure.Allure.step;

/*
ZADANIE DOMOWE

Wyciągnąć do configuration.properties ścieżkę, gdzie mają być zapisywane zrzuty ekranu
 */
public class BaseTest extends PropertiesConfig {
    ChromeDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        loadProperties();
        // wyłączenie logowania zbyt dużej informacji przy uruchamianiu drivera
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        //System.setProperty("webdriver.chrome.driver", "/Users/wojciechpotocki/Documents/szkolenia/testuj/bootcamp/driver/chromedriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        // uruchamianie testów bez włączania automatycznie przeglądarki
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        // maksymalizacja okna
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(properties.getProperty("url"));

        System.out.println(properties.getProperty("email"));
    }

    @AfterMethod
    public void testTearDown(ITestResult result) throws IOException {
        step("Zakończenie metody testowej");
        if (!result.isSuccess()) {
            System.out.println("Metoda testowa " + result.getMethod().getMethodName() + " zakończona niepowodzeniem");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
            String readableTimestamp = formatter.format(result.getEndMillis());
            String fileName = result.getMethod().getMethodName() + "_" + readableTimestamp + ".png";

            String screenshotPath = "./target/artifacts/screenshots/" + fileName;
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);

            // Dodanie załącznika do raportu
            InputStream screenshotInputStream = Files.newInputStream(Paths.get(screenshotPath));
            Allure.addAttachment(fileName, screenshotInputStream);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void finishTest() throws IOException {
        // na zakończenie testów kopiujemy definicję środowiska do folderu z rezultatami dla Allure
        String projectPath = System.getProperty("user.dir");
        FileUtils.copyFile(new File(projectPath + "/src/test/resources/environment.properties"),
                new File(projectPath + "/target/allure-results/environment.properties"));
        FileUtils.copyFile(new File(projectPath + "/src/test/resources/categories.json"),
                new File(projectPath + "/target/allure-results/categories.json"));
    }
}
