package tests.regression;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ManAllProductsRegressionTest extends BaseTest {


    @Test(groups = "regression")
    @Story("Product Navigation")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that clicking on a product opens the correct product detail page, 10 times.")
    @Step("Click on first product and verify details page")
    public void clickingOnProductBringsRightScreen10Times() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        for (int i = 0; i < 10; i++) {
            var productNameInCatalog = manCatalog.getProductTitleByIndex(i);
            var productDetailPage = manCatalog.clickCard(i);
            var productNameInDetailPage = productDetailPage.getNameOfProduct();
            Assert.assertEquals(productNameInDetailPage, productNameInCatalog, "The product" + productNameInCatalog + "in catalog should be the same in " +
                    "details page" + productNameInDetailPage);
            manCatalog = productDetailPage.openManCatalog();
        }
    }

    @Test(groups = "regression")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that price range filter works correctly")
    @Step("Apply price filter and verify results")
    public void priceRangeFilterWorksCorrectly() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        manCatalog.openFilterOptions();
        manCatalog.changePriceRangeFilterRandomly();
        var priceRange = manCatalog.getPriceRangeFilter();

        var products = manCatalog.getAllProducts();

        for (int i = 0; i < 10; i++) {
            var priceOfProduct = manCatalog.getPriceOfCardByWebElement(products.get(i));
            Assert.assertTrue(priceOfProduct >= priceRange[0] && priceOfProduct <= priceRange[1], "Price range " + priceRange[0] + " | " + priceRange[1] + " does not sut the product price " + priceOfProduct);
        }
        manCatalog.clearFilterOptions();
    }

    @Test(groups = "regression")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that price range filter works correctly, 3 Times.")
    @Step("Apply price filter and verify results")
    public void priceRangeFilterWorksCorrectly3Times() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        for (int i = 0; i < 3; i++) {
            manCatalog.openFilterOptions();
            manCatalog.changePriceRangeFilterRandomly();
            var priceRange = manCatalog.getPriceRangeFilter();

            var products = manCatalog.getAllProducts();

            for (int j = 0; j < 10; j++) {
                var priceOfProduct = manCatalog.getPriceOfCardByWebElement(products.get(j));
                Assert.assertTrue(priceOfProduct >= priceRange[0] && priceOfProduct <= priceRange[1], "Price range " + priceRange[0] + " | " + priceRange[1] + " does not sut the product price " + priceOfProduct);
            }
            manCatalog.clearFilterOptions();
        }
    }

    @Test(groups = "regression")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that category filter works correctly by index")
    @Step("Apply category filter by index and verify results")
    public void categoryFilterWorksCorrectlyByIndex() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        var nameOfCategoryList = manCatalog.getCategoryTitleByIndex(2);
        manCatalog.clickCategoryByIndex(2);

        var products = manCatalog.getAllProducts();
        for (String categoryName : nameOfCategoryList) {

            /* Substring, deleting last index. Categories contains 's' in the end.*/
            categoryName = categoryName.substring(0, categoryName.length() - 1);

            var result = manCatalog.anyProductContainsString(categoryName, products.subList(0, 10));
            Assert.assertTrue(result, String.format("Category %s contains %s products", categoryName, result));
        }

    }

    @Test(groups = "regression")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that category filter works correctly by name")
    @Step("Apply category filter by index and verify results")
    public void categoryFilterWorksCorrectlyByName() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        var category = manCatalog.getCategoryByName("shirts");
        var nameOfCategoryList = manCatalog.getCategoryTitleByWebElement(category);
        manCatalog.clickCategoryByWebElement(category);
        var products = manCatalog.getAllProducts();
        for (String categoryName : nameOfCategoryList) {

            /* Substring, deleting last index. Categories contains 's' in the end.*/
            categoryName = categoryName.substring(0, categoryName.length() - 1);

            var result = manCatalog.anyProductContainsString(categoryName, products.subList(0, 10));
            Assert.assertTrue(result, String.format("Category %s contains %s products", categoryName, result));
        }

    }
}
