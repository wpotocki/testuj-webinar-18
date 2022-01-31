package sampleshop.pages;

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
    public void orderElement() {
        productDetailsPage.selectSize("M")
                .selectColor("czarny")
                .clickAddToCartButton();
    }
}
