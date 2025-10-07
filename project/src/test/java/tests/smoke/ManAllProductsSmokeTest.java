package tests.smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ManAllProductsSmokeTest extends BaseTest {


    private final By productsList = By.cssSelector("ul.product-grid__product-list");

    @Test(groups = "smoke")
    public void pageOpensCorrectly() {
        var manProducts = manAllProductsPage.open().acceptCookiesIfPresent();
        Assert.assertTrue(manProducts.atManAllProductsPage(), "Man all products page should be opened");
    }

    @Test(groups = "smoke")
    public void thereIsAtLeastOneProduct() {
        var manProducts = manAllProductsPage.open().acceptCookiesIfPresent();
        var products = manProducts.findAll(productsList);
        Assert.assertFalse(products.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    public void clickingOnProductBringsRightScreen() {
        var manProducts = manAllProductsPage.open().acceptCookiesIfPresent();
        String baseSelector = productsList.toString().replace("By.cssSelector: ", "");
        By firstProduct = By.cssSelector(baseSelector + " li:nth-child(1)");
        var titleOfProductLocator = By.xpath("//li[contains(@data-productid,'462627330')]//h3");
        var titleOfProduct = manProducts.getText(titleOfProductLocator);
        manProducts.clickField(firstProduct);
        var titleOfProductInDetailsLocator = By.xpath("//div[@data-qa-id='product-detail-info']//h1");
        var titleOfProductInDetails = manProducts.getText(titleOfProductInDetailsLocator);
        Assert.assertEquals(titleOfProduct, titleOfProductInDetails);
    }
}
