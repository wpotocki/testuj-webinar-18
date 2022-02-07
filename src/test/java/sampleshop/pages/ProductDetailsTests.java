package sampleshop.pages;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
ZADANIE DOMOWE
- dodanie możliwości wskazania ilości produktów
 */
public class ProductDetailsTests extends BaseTest {
    private ProductDetailsPage productDetailsPage;

    @BeforeClass
    public void openPage() {
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @Test(description = "Zamawianie produkt - dodanie do koszyka")
    @Description("To jest opis do zamówienia elementu")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Zamawianie produktu")
    @Link(name = "link do dokumentacji", url = "http://webinar.edu/testuj/docs")
    @Issue("BUG-99")
    public void orderElement() {
        productDetailsPage.selectSize("M")
                .selectColor("czarny")
                .clickAddToCartButton();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Epic("EDU-001")
    @Story("EDU-111")
    @Link(name = "link do story", url = "http://webinar.edu/testuj/story")
    @TmsLink("TMS-12")
    public void thisMethodWillFail() {
        Assert.assertEquals(3, 3);
    }
}
