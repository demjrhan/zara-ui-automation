package tests.smoke;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ProductDetailSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Detail Page You May Like")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that you may like section exists and have at least 1 item.")
    @Step("Click on any product from category and verify you may like section.")
    public void ascendingFilterWorksCorrectly(){
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var details = manCatalog.clickRandomCard();
        details.scrollToInterestedProduct();
        Assert.assertNotEquals(details.getInterestedProductCount(), 0 , "There must be at least 1 product in you may be interested in products.");
    }

    @Test(groups = "smoke")
    @Story("Home Page Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that clicking the Zara logo brings user back to home page")
    @Step("Navigate to home page and click logo")
    public void testZaraHomeButtonShouldBringHomePage() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var details = manCatalog.clickRandomCard();
        details.returnHomePage();
        Assert.assertTrue(details.atHomePage(), "Should be at home page.");
    }

    @Test(groups = "smoke")
    @Story("Detail Page You May Like")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that clicking a recommended product opens that product detail page")
    @Step("Click on a recommended product and verify it opens")
    public void clickingRecommendedProductOpensProductDetailPage() {
        var manCatalog = homePage.open().acceptCookiesIfPresent().openManCatalog();
        var details = manCatalog.clickRandomCard();
        details.scrollToInterestedProduct();
        var firstRecommendedProductsTitle = details.getProductTitleOfInterestedByIndex(0);
        var newDetails = details.clickInterestedProductByIndex(0);
        var firstRecommendedProductTitleAfter = newDetails.getTitleOfProduct();
        Assert.assertEquals(firstRecommendedProductTitleAfter, firstRecommendedProductsTitle,
                String.format("The first recommended product title should be the same after opening the product. Expected: %s, Actual: %s",
                        firstRecommendedProductsTitle, firstRecommendedProductTitleAfter));    }

    @Test(groups = "smoke")
    @Story("Social Footer Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that social footer always has at least one link.")
    @Step("Check social footer count")
    public void checkSocialFooterCountOnLoadHomePage() {
        var details = homePage.open().acceptCookiesIfPresent().openManCatalog().clickRandomCard();
        details.scrollDownToSocialFooterSmooth();
        Assert.assertTrue(details.getSocialFooterLinkCount() > 0, "Social footer link count should be greater than zero.");
    }
}
