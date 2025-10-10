package tests.smoke;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("Zara Website Testing")
@Feature("Man Products Catalog")
public class ManAllProductsSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Catalog Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that man products catalog page opens correctly")
    @Step("Navigate to man catalog page")
    public void pageOpensCorrectly() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        Assert.assertTrue(manCatalog.atManAllProductsPage(), "Man all products page should be opened");
    }

    @Test(groups = "smoke")
    @Story("Product Display")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that at least one product is displayed in catalog")
    @Step("Check if products are loaded")
    public void thereIsAtLeastOneProduct() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var products = manCatalog.getAllProducts();
        Assert.assertFalse(products.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    @Story("Product Navigation")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that clicking on a product opens the correct product detail page")
    @Step("Click on first product and verify details page")
    public void clickingOnProductBringsRightScreen() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var productNameInCatalog = manCatalog.getProductTitleByIndex(1);
        var productDetailPage = manCatalog.clickCard(1);
        var productNameInDetailPage = productDetailPage.getNameOfProduct();
        Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                "details page" + productNameInDetailPage);
    }


    @Test(groups = "smoke")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that ascending filter works correctly")
    @Step("Apply ascending filter and verify results")
    public void ascendingFilterWorksCorrectly() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        manCatalog.sortByPriceDescending();
        var firstProductPriceDescending = manCatalog.getPriceOfCardByWebElement(manCatalog.getFirstProduct());

        manCatalog.clearFilterOptions();

        manCatalog.sortByPriceAscending();
        var firstProductPriceAscending = manCatalog.getPriceOfCardByWebElement(manCatalog.getFirstProduct());

        Assert.assertTrue(firstProductPriceDescending > firstProductPriceAscending, "Descending filter's first product should always be more expensive than ascending filter's first product. ");
    }
}
