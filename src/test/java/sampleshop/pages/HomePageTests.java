package sampleshop.pages;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test
    public void checkCategoriesInMenu() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkMenuItemExists("Clothes"));
        Assert.assertFalse(homePage.checkMenuItemExists("promocja"));
    }
}
