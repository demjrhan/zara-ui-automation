package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManAllProductsPage extends BasePage {

    private final By manAllProductsRoot = By.id("I2024-HOMBRE-VIEW-ALL-PRODUCTS-VER-TODO");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");

    public ManAllProductsPage(WebDriver driver) {
        super(driver);
    }

    public ManAllProductsPage open() {
        driver.get("https://www.zara.com/pl/en/man-all-products-l7465.html?v1=2443335");
        isVisible(manAllProductsRoot);
        return this;
    }

    public boolean atBasketPage() {
        return isVisible(manAllProductsRoot);
    }

    public ManAllProductsPage acceptCookiesIfPresent() {
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
    public ManAllProductsPage clickField(By locator) {
        click(locator);
        return this;
    }
    public WebElement findInside(WebElement parent, By childLocator) {
        return super.findInside(parent, childLocator);
    }

    public String getTextInside(WebElement parent, By childLocator) {
        return super.getTextInside(parent, childLocator);
    }
}
