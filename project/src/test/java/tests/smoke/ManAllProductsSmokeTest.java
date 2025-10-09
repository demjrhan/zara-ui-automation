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

    @Test(groups = "smoke")
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
            var priceOfProduct = manCatalog.getPriceOfCard(products.get(i));
            Assert.assertTrue(priceOfProduct >= priceRange[0] && priceOfProduct <= priceRange[1], "Price range " + priceRange[0] + " | " + priceRange[1] + " does not sut the product price " + priceOfProduct);
        }
        manCatalog.clearFilterOptions();
    }

    @Test(groups = "smoke")
    public void priceRangeFilterWorksCorrectly3Times() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        for (int i = 0; i < 3; i++) {
            manCatalog.openFilterOptions();
            manCatalog.changePriceRangeFilterRandomly();
            var priceRange = manCatalog.getPriceRangeFilter();

            var products = manCatalog.getAllProducts();

            for (int j = 0; j < 10; j++) {
                var priceOfProduct = manCatalog.getPriceOfCard(products.get(j));
                Assert.assertTrue(priceOfProduct >= priceRange[0] && priceOfProduct <= priceRange[1], "Price range " + priceRange[0] + " | " + priceRange[1] + " does not sut the product price " + priceOfProduct);
            }
            manCatalog.clearFilterOptions();
        }
    }

    @Test(groups = "smoke")
    @Story("Filter Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that category filter works correctly by index")
    @Step("Apply category filter by index and verify results")
    public void categoryFilterWorksCorrectlyByIndex(){
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        var category = manCatalog.getCategoryByIndex(2);
        var nameOfCategoryList = manCatalog.getCategory(category);
        manCatalog.click(category);
        var products = manCatalog.getAllProducts();
        for (String categoryName : nameOfCategoryList) {

            /* Substring, deleting last index. Categories contains 's' in the end.*/
            categoryName = categoryName.substring(0, categoryName.length() - 1);

            var result = manCatalog.anyProductContainsString(categoryName, products.subList(0, 10));
            Assert.assertTrue(result, String.format("Category %s contains %s products", categoryName, result));
        }

    }

    @Test(groups = "smoke")
    public void categoryFilterWorksCorrectlyByName(){
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();

        var category = manCatalog.getCategoryByName("shirts");
        var nameOfCategoryList = manCatalog.getCategory(category);
        manCatalog.click(category);
        var products = manCatalog.getAllProducts();
        for (String categoryName : nameOfCategoryList) {

            /* Substring, deleting last index. Categories contains 's' in the end.*/
            categoryName = categoryName.substring(0, categoryName.length() - 1);

            var result = manCatalog.anyProductContainsString(categoryName, products.subList(0, 10));
            Assert.assertTrue(result, String.format("Category %s contains %s products", categoryName, result));
        }

    }
}
