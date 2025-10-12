package pages;


import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    private final By homeRoot = By.id("I2024-HOME");
    private final By zaraLogo = By.xpath("//a[@data-qa-action='logo-click']");
    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    private final By shoppingCart = By.xpath("//a[@data-qa-id='layout-header-go-to-cart']");

    private final By manNavigationButton = By.xpath("//a[contains(@data-categoryid,'1885841')]");
    private final By manViewAllButton = By.xpath("//li[contains(@data-categoryid,'2431932')]");
    private final By manProductsList = By.cssSelector("ul.product-grid__product-list li");

    private final By searchBox = By.xpath("//a[@data-qa-id='header-search-text-link']");
    private final By searchInputField = By.id("search-home-form-combo-input");
    private final By productsList = By.cssSelector("ul.product-grid__product-list li");

    private final By footer = By.cssSelector("footer.layout-footer");
    private final By socialFooter = By.cssSelector("ul#homeSocialFooter li");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.zara.com/pl/en/");
        isVisible(homeRoot);
        return this;
    }

    public ManAllCatalogPage openManCatalog() {
        findVisibility(navigationButton).click();
        findVisibility(manNavigationButton).click();
        findVisibility(manViewAllButton).click();
        waitUntilVisible(manProductsList);
        return new ManAllCatalogPage(driver);
    }

    public HomePage clickLogo() {
        click(zaraLogo);
        return this;
    }

    public void refresh() {
        super.refresh();
    }

    public void clickSearch() {
        waitUntilVisible(searchBox);
        click(searchBox);
    }

    public void clickSearchInputField() {
        waitUntilVisible(searchInputField);
        click(searchInputField);
    }

    public boolean searchBoxIsVisible() {
        return isVisible(searchBox);
    }

    public boolean searchInputFieldIsVisible() {
        return isVisible(searchInputField);
    }

    public HomePage searchProduct(String product) {
        clickSearch();
        clickSearchInputField();
        searchTextOnSearchInputField(product);
        return this;
    }

    public boolean searchInputFieldIsEmpty() {
        return isEmpty(searchInputField);
    }

    public void searchTextOnSearchInputField(String text) {
        type(searchInputField, text);
        sendEnter(searchInputField);
    }

    public boolean atHomePage() {
        return isVisible(homeRoot);
    }

    public List<WebElement> getAllProductsAfterSearch() {
        return findAllPresence(productsList);
    }

    public int getProductCountAfterSearch() {
        return findAllPresence(productsList).size();
    }

    public boolean shoppingCartIsVisible() {
        return isVisible(shoppingCart);
    }

    public boolean shoppingCartIsClickable() {
        return isClickable(shoppingCart);
    }

    public HomePage acceptCookiesIfPresent() {
        if (isVisible(acceptCookiesButton)) click(acceptCookiesButton);
        return this;
    }

    public boolean acceptCookiesButtonIsVisible() {
        return isVisible(acceptCookiesButton);
    }

    public void scrollDownToFooterSmooth() {
        scrollToElementSmooth(findPresence(footer));
    }

    public void scrollDownToSocialFooterSmooth() {
        scrollToElementSmooth(findPresence(socialFooter));
    }

    public int getSocialFooterLinkCount() {
        return findAllPresence(socialFooter).size();
    }

    public List<String> getSocialFooterLinkNames() {
        var links = findAllPresence(socialFooter);
        var names = new ArrayList<String>();
        links.forEach(link ->{
            var name = getTextInside(link, By.cssSelector("a"));
            if (!name.isEmpty()) names.add(name);
                });
        return names;
    }

}
