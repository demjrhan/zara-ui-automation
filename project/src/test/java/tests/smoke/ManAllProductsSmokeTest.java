package tests.smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ManAllProductsSmokeTest extends BaseTest {



    @Test(groups = "smoke")
    public void pageOpensCorrectly() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        Assert.assertTrue(manCatalog.atManAllProductsPage(), "Man all products page should be opened");
    }

    @Test(groups = "smoke")
    public void thereIsAtLeastOneProduct() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var products = manCatalog.getAllProducts();
        Assert.assertFalse(products.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    public void clickingOnProductBringsRightScreen() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var productNameInCatalog = manCatalog.getCardTitle(1,By.cssSelector("h3"));
        var productDetailPage = manCatalog.clickCard(1);
        var productNameInDetailPage = productDetailPage.getNameOfProduct();
        Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                "details page" + productNameInDetailPage);    }

    @Test(groups = "smoke")
    public void clickingOnProductBringsRightScreen10() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        for (int i = 0 ; i < 10 ; i++) {
            var productNameInCatalog = manCatalog.getCardTitle(i,By.cssSelector("h3"));
            var productDetailPage = manCatalog.clickCard(i);
            var productNameInDetailPage = productDetailPage.getNameOfProduct();
            Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                    "details page" + productNameInDetailPage);
            manCatalog = productDetailPage.openManCatalog();
        }
    }

    /* TODO need to check if lower and higher price range filters works as expected */
    @Test(groups = "smoke")
    public void priceRangeFilterWorksCorrectly() throws InterruptedException {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        manCatalog.clickFilterButton();
        manCatalog.decreaseMaxPriceByPixelsFromRight(25);
    }
}
