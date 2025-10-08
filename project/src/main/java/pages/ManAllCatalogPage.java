package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManAllCatalogPage extends BasePage {

    private final By manAllProductsRoot = By.id("I2024-HOMBRE-VIEW-ALL-PRODUCTS-VER-TODO");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    private final By productsList = By.cssSelector("ul.product-grid__product-list li");
    private final By filterButton = By.xpath("//button[contains(@data-qa-action,'filters-button')]");

    private final By priceFilterLowerBound = By.id("zds-slider-thumb-6870");
    private final By priceFilterHigherBound = By.id("zds-slider-thumb-6871");

    public ManAllCatalogPage(WebDriver driver) {
        super(driver);
    }

    public ManAllCatalogPage open() {
        driver.get("https://www.zara.com/pl/en/man-all-products-l7465.html?v1=2443335");
        isVisible(manAllProductsRoot);
        return this;
    }

    public boolean atBasketPage() {
        return isVisible(manAllProductsRoot);
    }

    public ManAllCatalogPage acceptCookiesIfPresent() {
        if (isVisible(acceptCookiesButton)) click(acceptCookiesButton);
        return this;
    }

    public boolean isVisible(By locator) {
        return super.isVisible(locator);
    }

    public boolean atManAllProductsPage() {
        return isVisible(manAllProductsRoot);
    }

    public List<WebElement> findAll(By locator) {
        return super.findAll(locator);
    }

    public WebElement find(By locator) {
        return super.find(locator);
    }

    public String getText(By locator) {
        return super.getText(locator);
    }

    public void click(By locator) {
        super.click(locator);
    }
    public WebElement findInside(WebElement parent, By childLocator) {
        return super.findInside(parent, childLocator);
    }

    public String getTextInside(WebElement parent, By childLocator) {
        return super.getTextInside(parent, childLocator);
    }

    public List<WebElement> getAllProducts() {
        return findAll(productsList);
    }

    public String getCardTitle(int index, By locator) {
        var cards = findAll(productsList);
        return findInside(cards.get(index), locator).getText().trim();
    }

    public ProductDetailPage clickCard(int index) {
        var cards = findAll(productsList);
        var product = cards.get(index);
        product.click();
        return new ProductDetailPage(driver);
    }

    public void clickFilterButton(){
        click(filterButton);
    }
}
