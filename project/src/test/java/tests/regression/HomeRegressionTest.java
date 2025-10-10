package tests.regression;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

@Epic("Zara Website Testing")
@Feature("Home Page Regression Tests")
public class HomeRegressionTest extends BaseTest {

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By searchTrigger = By.xpath("//a[@data-qa-id='header-search-text-link']");
    private final By searchInput = By.id("search-home-form-combo-input");
    private final By searchedItemList = By.xpath("//ul[@id='search-home-form-combo-menu']");
    private final By shoppingCart = By.xpath("//a[@data-qa-id='layout-header-go-to-cart']");

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
        Assert.assertTrue(home.isNotVisible(acceptCookiesButton),
                "Cookies button should not be visible after refresh.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify search functionality works correctly")
    @Step("Click search and type text")
    public void testClickSearchAndWrite() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.click(searchTrigger);
        Assert.assertTrue(home.isVisible(searchInput), "Search field should be visible.");
        home.click(searchInput);
        home.type(searchInput, "Blazer");
        Assert.assertFalse(home.isEmpty(searchInput), "Search field should contain text.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search trigger disappears after opening search input")
    @Step("Click search trigger and verify it becomes invisible")
    public void testSearchTriggerIsNotVisibleAfterSearchInputOpened() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.click(searchTrigger);
        Assert.assertTrue(home.isNotVisible(searchTrigger),
                "Search trigger should not be visible after opening search input.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that search results populate after typing valid search term")
    @Step("Search for 'Blazer' and verify results appear")
    public void testAfterSearchItemListPopulates() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.click(searchTrigger);
        home.click(searchInput);
        home.type(searchInput, "Blazer");
        var items = home.findAll(searchedItemList);
        Assert.assertFalse(items.isEmpty(), "Search item list should contain items.");
    }

    @Test(groups = "regression")
    @Story("Search Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that invalid search terms don't show results")
    @Step("Search for random text and verify no results appear")
    public void testAfterWriteRandomTextSearchFieldItemListDoesntPopulate() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.click(searchTrigger);
        home.click(searchInput);
        home.type(searchInput, "random-text");
        Assert.assertTrue(home.isNotVisible(searchedItemList),
                "Search item list should not be visible after invalid search.");
    }

}
