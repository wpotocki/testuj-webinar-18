package sampleshop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static io.qameta.allure.Allure.step;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "group_1")
    private WebElement sizeSelect;

    @FindBy(css = "button[data-button-action='add-to-cart']")
    private WebElement addToCartButton;

    @FindAll({@FindBy(className = "input-color")}   )
    private List<WebElement> colors;

    @Step("Wybór rozmiaru")
    public ProductDetailsPage selectSize(String sizeToSelect) {
        step("Wybór rozmiaru");
        LOGGER.debug("Wybieram rozmiar w trybie DEBUG " + sizeToSelect);
        LOGGER.info("Wybieram rozmiar " + sizeToSelect);
        Select select = new Select(sizeSelect);
        select.selectByVisibleText(sizeToSelect);

        return this;
    }

    public void clickAddToCartButton() {
        step("Dodanie do koszyka");
        buttonClick(addToCartButton);
    }

    public ProductDetailsPage selectColor(String color) {
        step("Wybór koloru");
        colors.forEach(c -> {
            if (color.equals(c.getAttribute("title"))) {
                c.click();
            }
        });

        return this;
    }
}
