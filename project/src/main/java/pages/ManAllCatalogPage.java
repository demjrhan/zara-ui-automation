package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ManAllCatalogPage extends BasePage {

    private final By manAllProductsRoot = By.id("I2024-HOMBRE-VIEW-ALL-PRODUCTS-VER-TODO");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    private final By productsList = By.cssSelector("ul.product-grid__product-list li");
    private final By filterButton = By.xpath("//button[contains(@data-qa-action,'filters-button')]");

    private final By priceFilterLocator = By.xpath("//input[contains(@id,'zds-slider-thumb')]");

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

    /* TODO, copied from GPT need to learn how to manage problematic sliders. Apparently Zara's slider thumbs are not drag and droppable. */
    public void decreaseMaxPriceByPixelsFromRight(int byPixels) throws InterruptedException {
        By sliderTrack = By.cssSelector(".zds-slider-track");

        wait.until(ExpectedConditions.visibilityOfElementLocated(sliderTrack));
        WebElement track = find(sliderTrack);

        // ensure track is visible on screen
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", track);

        int width = track.getSize().getWidth();

        // first, click very near the right edge to ensure the MAX thumb is selected
        int nearRightFromCenter = (width / 2) - 2; // offset from center to near-right
        actions.moveToElement(track, nearRightFromCenter, 0).click().perform();

        // now click at a point 'byPixels' left from the right edge
        int targetFromLeft = Math.max(0, width - byPixels);
        int offsetFromCenter = targetFromLeft - (width / 2);

        actions.moveToElement(track, offsetFromCenter, 0).click().perform();

        Thread.sleep(1500);
    }

}
