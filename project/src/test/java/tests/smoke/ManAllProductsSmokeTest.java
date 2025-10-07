package tests.smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ManAllProductsSmokeTest extends BaseTest {


    private final By productsList = By.cssSelector("ul.product-grid__product-list li");

    @Test(groups = "smoke")
    public void pageOpensCorrectly() {
        var manProducts = homePage.open().acceptCookiesIfPresent().goToManAllProducts();
        Assert.assertTrue(manProducts.atManAllProductsPage(), "Man all products page should be opened");
    }

    @Test(groups = "smoke")
    public void thereIsAtLeastOneProduct() {
        var manProducts = homePage.open().acceptCookiesIfPresent().goToManAllProducts();
        var products = manProducts.findAll(productsList);
        Assert.assertFalse(products.isEmpty(), "There should be at least one product");
    }

    /* TODO should create page for product detail */
    @Test(groups = "smoke")
    public void clickingOnProductBringsRightScreen() {
        var manProducts = homePage.open().acceptCookiesIfPresent().goToManAllProducts();
        var products = manProducts.findAll(productsList);
        var firstProduct = products.getFirst();
        var firstProductName = manProducts.getTextInside(firstProduct,By.cssSelector("h3"));

        firstProduct.click();
        var secondProduct = manProducts.find(By.cssSelector(".product-detail-info__header-name"));
        var secondProductName = secondProduct.getText().trim();
        Assert.assertEquals(secondProductName, firstProductName);
    }
}
