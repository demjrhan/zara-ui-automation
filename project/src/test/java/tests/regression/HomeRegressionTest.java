package tests.regression;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("Zara Website Testing")
@Feature("Home Page Regression Tests")
public class HomeRegressionTest extends BaseTest {



    @Test(groups = "regression")
    @Story("Home Page Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that clicking the Zara logo multiple times maintains consistency")
    @Step("Click logo multiple times and verify home page")
    public void testZaraHomeButtonConsistency() {
        var home = homePage.open().acceptCookiesIfPresent();
        for (int i = 0; i < 25; i++) {
            home = home.clickLogo();
        }
        Assert.assertTrue(home.atHomePage(), "Should stay at home page.");
    }

    @Test(groups = "regression")
    @Story("Cookie Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that cookies are not shown again after refresh")
    @Step("Accept cookies and refresh page")
    public void testAcceptCookiesIsNotPresentAfterRefresh() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.refresh();
        Assert.assertTrue(home.acceptCookiesButtonIsVisible(),
                "Cookies button should not be visible after refresh.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search functionality works correctly")
    @Step("Click search and type text")
    public void testClickSearchAndWrite() {
        var home = homePage.open().acceptCookiesIfPresent();
        home = home.searchProduct("Blazer");
        Assert.assertFalse(home.searchInputFieldIsEmpty(), "Search field should contain text.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search trigger disappears after opening search input")
    @Step("Click search trigger and verify it becomes invisible")
    public void testSearchTriggerIsNotVisibleAfterSearchInputOpened() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.clickSearch();
        Assert.assertFalse(home.searchBoxIsVisible(),
                "Search trigger should not be visible after opening search input.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that search results populate after typing valid search term")
    @Step("Search for 'Blazer' and verify results appear")
    public void testAfterSearchItemListPopulates() {
        var home = homePage.open().acceptCookiesIfPresent();
        home = home.searchProduct("Blazer");
        var items = home.getAllProductsAfterSearch();
        Assert.assertFalse(items.isEmpty(), "Search item list should contain items.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that invalid search terms don't show results")
    @Step("Search for random text and verify no results appear")
    public void testAfterWriteRandomTextSearchFieldItemListDoesntPopulate(){
        var home = homePage.open().acceptCookiesIfPresent();
        home = home.searchProduct("random-text");
        Assert.assertEquals(home.getProductCountAfterSearch(), 0, "Search item list should be empty after invalid search.");
    }
    @Test(groups = "regression")
    @Story("Social Footer Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that social footer names always match.")
    @Step("Check social footer names are matching with man catalog.")
    public void verifySocialFooterLinksMatchWithManCatalog() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.scrollDownToSocialFooterSmooth();
        var homeFooterNames = home.getSocialFooterLinkNames();
        var manCatalog = home.openManCatalog();
        var manCatalogNames = manCatalog.getSocialFooterLinkNames();

        Assert.assertEquals(homeFooterNames,manCatalogNames, "Social footer link names should be the same.");
    }
    @Test(groups = "regression")
    @Story("Social Footer Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that social footer names always match.")
    @Step("Check social footer names are matching with man catalog.")
    public void verifySocialFooterLinksMatchWithProductDetails() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.scrollDownToSocialFooterSmooth();
        var homeFooterNames = home.getSocialFooterLinkNames();
        var details = home.openManCatalog().clickRandomCard();
        var detailsNames = details.getSocialFooterLinkNames();

        Assert.assertEquals(homeFooterNames,detailsNames, "Social footer link names should be the same.");
    }
}
