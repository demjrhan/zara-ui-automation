package tests.smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class HomeSmokeTest extends BaseTest {

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By searchTrigger = By.xpath("//a[@data-qa-id='header-search-text-link']");
    private final By shoppingCart = By.xpath("//a[@data-qa-id='layout-header-go-to-cart']");
    private final By zaraLogo = By.xpath("//a[@data-qa-action='logo-click']");

    @Test(groups = "smoke")
    public void testZaraHomeButtonShouldBringHomePage() {
        var home = homePage.open().acceptCookiesIfPresent();
        Assert.assertTrue(home.atHomePage(), "Should be at home page.");
        var newPage = home.clickLogo();
        Assert.assertTrue(newPage.atHomePage(), "Should stay at home page.");
    }

    @Test(groups = "smoke")
    public void testSearchTriggerIsVisibleOnLoadHomePage() {
        var home = homePage.open().acceptCookiesIfPresent();
        Assert.assertTrue(home.isVisible(searchTrigger), "Search trigger field should be visible.");
    }

    @Test(groups = "smoke")
    public void testShoppingCartShouldBeVisibleOnLoadHomePage() {
        var home = homePage.open().acceptCookiesIfPresent();
        Assert.assertTrue(home.isVisible(shoppingCart), "Shopping cart should be visible.");
    }

    @Test(groups = "smoke")
    public void testShoppingCartShouldBeClickableOnLoadHomePage() {
        var home = homePage.open().acceptCookiesIfPresent();
        Assert.assertTrue(home.isClickable(shoppingCart), "Shopping cart should be clickable.");
    }

    /*@Test(groups = "smoke")
    public void testClickingOnShoppingCartButtonBringsShoppingCartPage() {
        var cart = homePage.open().acceptCookiesIfPresent().goToShoppingCartPage();
        Assert.assertTrue(cart.atShoppingCartPage(), "Shopping cart should be opened.");
    }*/
}
