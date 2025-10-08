package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ManAllCatalogPage extends BasePage {

    private final By manAllProductsRoot = By.id("I2024-HOMBRE-VIEW-ALL-PRODUCTS-VER-TODO");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    private final By productsList = By.cssSelector("ul.product-grid__product-list li");
    private final By filterButton = By.xpath("//button[contains(@data-qa-action,'filters-button')]");
    private final By closeFilterButton = By.xpath("//div[contains(@class,'zds-drawer-header')]//button[contains(@aria-label,'close')]");
    private final By clearFilterButton = By.xpath("//button[contains(@data-qa-action,'filters-clear')]");

    private final By priceSliderLocator = By.cssSelector(".zds-slider-track");
    private final By priceSliderThumbsLocator = By.xpath("//input[contains(@id,'zds-slider-thumb')]");

    private final By priceTagOfCard = By.cssSelector("span.money-amount__main");

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

    public String getCardTitleByIndex(int index, By locator) {
        var cards = findAll(productsList);
        return findInside(cards.get(index), locator).getText().trim();
    }

    public ProductDetailPage clickCard(int index) {
        var cards = findAll(productsList);
        var product = cards.get(index);
        product.click();
        return new ProductDetailPage(driver);
    }

    public void openFilterOptions() {
        click(filterButton);
    }

    public void clearFilterOptions() {
        click(clearFilterButton);
    }

    public void closeFilterOptions() {
        click(closeFilterButton);
    }

    public int getPriceOfCard(WebElement element) {
        return getPriceFromFullString(getTextInside(element, priceTagOfCard));
    }

    public int getPriceFromFullString(String string) {
        string = string.replace("\u00A0", "");
        string = string.replace("PLN", "").trim();
        string = string.substring(0, string.length() - 3);
        string = string.replace(".", "");
        string = string.replace(",", "");
        return Integer.parseInt(string);
    }

    public int[] getPriceRangeFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceSliderLocator));
        var priceThumbs = findAll(priceSliderThumbsLocator);

        var lowLimit = priceThumbs.getFirst().getAttribute("aria-valuetext").trim();
        var highLimit = priceThumbs.getLast().getAttribute("aria-valuetext").trim();

        var lowLimitPrice = getPriceFromFullString(lowLimit);
        var highLimitPrice = getPriceFromFullString(highLimit);

        return new int[]{lowLimitPrice, highLimitPrice};
    }


    public void changePriceRangeFilterRandomly() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceSliderLocator));
        WebElement track = find(priceSliderLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", track);
        int width = track.getSize().getWidth();
        int nearRightFromCenter = (width / 2) - 2;
        actions.moveToElement(track, nearRightFromCenter, 0).click().perform();

        Random random = new Random();
        int randomThreshold = width / 2 + random.nextInt(width / 2 + 1);
        int targetFromLeft = Math.max(0, width - randomThreshold);
        int offsetFromCenter = targetFromLeft - (width / 2);

        actions.moveToElement(track, offsetFromCenter, 0).click().perform();
    }


}
