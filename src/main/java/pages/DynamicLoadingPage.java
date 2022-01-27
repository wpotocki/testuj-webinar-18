package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicLoadingPage extends BasePage {
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    public static final String EXPECTED_CONFIRMATION_TEXT = "Hello World!";

    @FindBy(css = "#start > button")
    private WebElement startButton;

    @FindBy(xpath = "//*[@id=\"finish\"]/h4")
    private WebElement finishTextHeader;

    @FindBy(id = "loading")
    private WebElement loadingBar;

    public void clickStartButton() {
        startButton.click();
    }

    public String getFinishLoadingConfirmation() {
        return finishTextHeader.getText();
    }

}
