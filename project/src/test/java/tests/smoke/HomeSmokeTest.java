package tests.smoke;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("Zara Website Testing")
@Feature("Home Page Functionality")
public class HomeSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Home Page Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that clicking the Zara logo brings user back to home page")
    @Step("Navigate to home page and click logo")
    public void testZaraHomeButtonShouldBringHomePage() {
        var home = homePage.open()
                .acceptCookiesIfPresent();
        Assert.assertTrue(home.atHomePage(), "Should be at home page.");
        var newPage = home.clickLogo();
        Assert.assertTrue(newPage.atHomePage(), "Should stay at home page.");
    }

    @Test(groups = "smoke")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that search trigger is visible on home page load")
    @Step("Check search trigger visibility")
    public void testSearchTriggerIsVisibleOnLoadHomePage() {
        var home = homePage.open()
                .acceptCookiesIfPresent();
        Assert.assertTrue(home.searchBoxIsVisible(), "Search trigger field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Shopping Cart Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that shopping cart is visible on home page load")
    @Step("Check shopping cart visibility")
    public void testShoppingCartShouldBeVisibleOnLoadHomePage() {
        var home = homePage.open()
                .acceptCookiesIfPresent();
        Assert.assertTrue(home.shoppingCartIsVisible(), "Shopping cart should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Shopping Cart Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that shopping cart is clickable on home page load")
    @Step("Check shopping cart click ability")
    public void testShoppingCartShouldBeClickableOnLoadHomePage() {
        var home = homePage.open()
                .acceptCookiesIfPresent();
        Assert.assertTrue(home.shoppingCartIsClickable(), "Shopping cart should be clickable.");
    }

    @Test(groups = "smoke")
    @Story("Social Footer Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that social footer always has at least one link.")
    @Step("Check social footer count")
    public void checkSocialFooterCountOnLoadHomePage() {
        var home = homePage.open()
                .acceptCookiesIfPresent();
        home.scrollDownToSocialFooterSmooth();
        Assert.assertTrue(home.getSocialFooterLinkCount() > 0, "Social footer link count should be greater than zero.");
    }


}
