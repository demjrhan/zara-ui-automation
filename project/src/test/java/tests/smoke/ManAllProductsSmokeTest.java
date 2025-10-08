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
        var productNameInCatalog = manCatalog.getCardTitleByIndex(1,By.cssSelector("h3"));
        var productDetailPage = manCatalog.clickCard(1);
        var productNameInDetailPage = productDetailPage.getNameOfProduct();
        Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                "details page" + productNameInDetailPage);    }

    @Test(groups = "smoke")
    public void clickingOnProductBringsRightScreen10() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        for (int i = 0 ; i < 10 ; i++) {
            var productNameInCatalog = manCatalog.getCardTitleByIndex(i,By.cssSelector("h3"));
            var productDetailPage = manCatalog.clickCard(i);
            var productNameInDetailPage = productDetailPage.getNameOfProduct();
            Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                    "details page" + productNameInDetailPage);
            manCatalog = productDetailPage.openManCatalog();
        }
    }

    @Test(groups = "smoke")
    public void priceRangeFilterWorksCorrectly() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        for (int i = 0 ; i < 3 ; i++) {
            manCatalog.openFilterOptions();
            manCatalog.changePriceRangeFilterRandomly();
            var priceRange = manCatalog.getPriceRangeFilter();

            var products = manCatalog.getAllProducts();

            for (int j = 0 ; j < 10 ; j++ ){
                var priceOfProduct = manCatalog.getPriceOfCard(products.get(j));
                Assert.assertTrue(priceOfProduct >= priceRange[0] && priceOfProduct <= priceRange[1], "Price range " + priceRange[0]+ " | " + priceRange[1] + " does not sut the product price " + priceOfProduct);
            }
            manCatalog.clearFilterOptions();
        }
    }
}
