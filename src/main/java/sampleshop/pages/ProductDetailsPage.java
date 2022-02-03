package sampleshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public ProductDetailsPage selectSize(String sizeToSelect) {
        LOGGER.debug("Wybieram rozmiar w trybie DEBUG " + sizeToSelect);
        LOGGER.info("Wybieram rozmiar " + sizeToSelect);
        Select select = new Select(sizeSelect);
        select.selectByVisibleText(sizeToSelect);

        return this;
    }

    public void clickAddToCartButton() {
        buttonClick(addToCartButton);
    }

    public ProductDetailsPage selectColor(String color) {
        colors.forEach(c -> {
            if (color.equals(c.getAttribute("title"))) {
                c.click();
            }
        });

        return this;
    }
}
