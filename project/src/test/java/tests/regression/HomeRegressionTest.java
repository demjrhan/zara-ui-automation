package tests.regression;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class HomeRegressionTest extends BaseTest {

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By searchTrigger = By.xpath("//a[@data-qa-id='header-search-text-link']");
    private final By searchInput = By.id("search-home-form-combo-input");
    private final By searchedItemList = By.xpath("//ul[@id='search-home-form-combo-menu']");
    private final By shoppingCart = By.xpath("//a[@data-qa-id='layout-header-go-to-cart']");

    @Test(groups = "regression")
    public void testZaraHomeButtonConsistency() {
        var home = homePage.open().acceptCookiesIfPresent();
        for (int i = 0; i < 25; i++) {
            home = home.clickLogo();
        }
        Assert.assertTrue(home.atHomePage(), "Should stay at home page.");
    }

    @Test(groups = "regression")
    public void testAcceptCookiesIsNotPresentAfterRefresh() {
        var home = homePage.open().acceptCookiesIfPresent();
        home.refresh();
        Assert.assertTrue(home.isNotVisible(acceptCookiesButton),
                "Cookies button should not be visible after refresh.");
    }

    @Test(groups = "regression")
    public void testClickSearchAndWrite() {
        var home = homePage.open().acceptCookiesIfPresent().clickField(searchTrigger);
        Assert.assertTrue(home.isVisible(searchInput), "Search field should be visible.");
        home.clickField(searchInput).type(searchInput, "Blazer");
        Assert.assertFalse(home.isEmpty(searchInput), "Search field should contain text.");
    }

    @Test(groups = "regression")
    public void testSearchTriggerIsNotVisibleAfterSearchInputOpened() {
        var home = homePage.open().acceptCookiesIfPresent().clickField(searchTrigger);
        Assert.assertTrue(home.isNotVisible(searchTrigger),
                "Search trigger should not be visible after opening search input.");
    }

    @Test(groups = "regression")
    public void testAfterSearchItemListPopulates() {
        var home = homePage.open().acceptCookiesIfPresent().clickField(searchTrigger);
        home.clickField(searchInput).type(searchInput, "Blazer");
        var items = home.findAll(searchedItemList);
        Assert.assertFalse(items.isEmpty(), "Search item list should contain items.");
    }

    @Test(groups = "regression")
    public void testAfterWriteRandomTextSearchFieldItemListDoesntPopulate() {
        var home = homePage.open().acceptCookiesIfPresent().clickField(searchTrigger);
        home.clickField(searchInput).type(searchInput, "random-text");
        Assert.assertTrue(home.isNotVisible(searchedItemList),
                "Search item list should not be visible after invalid search.");
    }

   /* @Test(groups = "regression")
    public void testAfterOpeningShoppingCartZaraLogoShouldBringHomePage() {
        var cart = homePage.open().acceptCookiesIfPresent().goToShoppingCartPage();
        var home = cart.goHomePage();
        Assert.assertTrue(home.atHomePage(), "Should return to home page.");
    }

    @Test(groups = "regression")
    public void testAfterOpeningShoppingCartGoingBackHomePageConsistency() {
        for (int i = 0; i < 5; i++) {
            var home = homePage.open().acceptCookiesIfPresent()
                    .goToShoppingCartPage().goHomePage();
            Assert.assertTrue(home.atHomePage(), "Should open home page.");
        }
    }*/
}
