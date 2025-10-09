package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ManAllCatalogPage extends BasePage {

    private final By manAllProductsRoot = By.id("I2024-HOMBRE-VIEW-ALL-PRODUCTS-VER-TODO");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    private final By productsList = By.cssSelector("ul.product-grid__product-list li");
    private final By filterButton = By.xpath("//button[contains(@data-qa-action,'filters-button')]");
    private final By closeFilterButton = By.xpath("//div[contains(@class,'zds-drawer-header')]//button[contains(@aria-label,'close')]");
    private final By clearFilterButton = By.xpath("//button[contains(@data-qa-action,'filters-clear')]");

    private final By priceSlider = By.cssSelector(".zds-slider-track");
    private final By priceSliderThumbs = By.xpath("//input[contains(@id,'zds-slider-thumb')]");

    private final By priceTagOfCard = By.cssSelector("span.money-amount__main");
    private final By nameOfCategory = By.cssSelector("a");

    private final By topBarCategories = By.xpath("//nav[contains(@class,'category-topbar-related-categories')]//ul/li");

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
    public void click(WebElement element) {
        super.click(element);
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

    public String getProductTitleByIndex(int index) {
        var cards = findAll(productsList);
        return getTextInside(cards.get(index), By.cssSelector("h3"));
    }
    public String getProductTitleByWebElement(WebElement element) {
        return getTextInside(element, By.cssSelector("h3"));
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
        waitUntilVisible(priceSlider);
        var priceThumbs = findAll(priceSliderThumbs);

        var lowLimit = priceThumbs.getFirst().getAttribute("aria-valuetext").trim();
        var highLimit = priceThumbs.getLast().getAttribute("aria-valuetext").trim();

        var lowLimitPrice = getPriceFromFullString(lowLimit);
        var highLimitPrice = getPriceFromFullString(highLimit);

        return new int[]{lowLimitPrice, highLimitPrice};
    }


    public void changePriceRangeFilterRandomly() {
        waitUntilVisible(priceSliderThumbs);
        WebElement track = find(priceSlider);
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

    public List<WebElement> getAllCategories() {
        waitUntilVisible(topBarCategories);
        return findAll(topBarCategories);
    }
    public WebElement getCategoryByIndex(int index) {
        waitUntilVisible(topBarCategories);
        return findAll(topBarCategories).get(index);
    }
    public WebElement getCategoryByName(String name) {
        name = name.toLowerCase().trim();
        waitUntilVisible(topBarCategories);
        var categories =  findAll(topBarCategories);
        for (WebElement category : categories) {
            var categoryNames = getCategory(category);
            for (var categoryName : categoryNames) {
                categoryName = categoryName.toLowerCase().trim();
                if (categoryName.contains(name) || categoryName.startsWith(name) || categoryName.endsWith(name)) {
                    return category;
                }
            }
        }
        return null;
    }

    public List<String> getCategory(WebElement element) {
        return getCategoryTitleFromFullString(getTextInside(element, nameOfCategory));
    }

    public List<String> getCategoryTitleFromFullString(String string) {
        if (string.contains("|")) {
            String[] parts = string.split("\\|");
            return Arrays.stream(parts).toList();
        }
        if (string.contains("-")) {
            String[] parts = string.split("-");
            return List.of(parts[1]);
        }
        if (string.contains(" ")) {
            String[] parts = string.split(" ");
            return Arrays.stream(parts).toList();
        }
        return List.of(string);

    }

    public boolean anyProductContainsString(String keyword, List<WebElement> list) {
        keyword = keyword.toLowerCase().trim();
        for (WebElement element : list) {
            var title = getProductTitleByWebElement(element).toLowerCase().trim();
            if (title.contains(keyword) || title.startsWith(keyword) || title.endsWith(keyword)) {
                return true;
            }

        }
        return false;
    }



}
