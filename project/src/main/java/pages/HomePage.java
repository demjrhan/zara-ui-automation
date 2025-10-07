package pages;


import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By shoppingCart = By.xpath("//a[@data-qa-id='layout-header-go-to-cart']");
    private final By zaraLogo = By.xpath("//a[@data-qa-action='logo-click']");
    private final By homeRoot = By.id("I2024-HOME");
    private final By shoppingCartId = By.xpath("//*[@id='shopCartView']");
    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");
    private final By manNavigationButton = By.xpath("//a[contains(@data-categoryid,'1885841')]");
    private final By manViewAllButton = By.xpath("//li[contains(@data-categoryid,'2431932')]')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.zara.com/pl/en/");
        isVisible(homeRoot);
        return this;
    }

    public ManAllProductsPage goToManAllProducts() {
        find(navigationButton).click();
        find(manNavigationButton).click();
        find(manViewAllButton).click();
        return new ManAllProductsPage(driver);
    }

    public HomePage clickLogo() {
        clickField(zaraLogo);
        return this;
    }

    public boolean atHomePage() {
        return isVisible(homeRoot);
    }

    public HomePage clickField(By locator) {
        click(locator);
        return this;
    }

    public List<WebElement> findAll(By locator) {
        return super.findAll(locator);
    }

    public void type(By locator, String text) {
        super.type(locator, text);
    }

    public HomePage acceptCookiesIfPresent() {
        if (isVisible(acceptCookiesButton)) click(acceptCookiesButton);
        return this;
    }
    public void refresh() {
         super.refresh();
    }

    public boolean isVisible(By locator) {
        return super.isVisible(locator);
    }

    public boolean isNotVisible(By locator) {
        return super.isNotVisible(locator);
    }

    public boolean isPresent(By locator) {
        return super.isPresent(locator);
    }

    public boolean isEmpty(By locator) {
        return super.isEmpty(locator);
    }

    public boolean isClickable(By locator) {
        return super.isClickable(locator);
    }

    public void waitForTitleContains(String title) {
        super.waitForTitleContains(title);
    }

    protected void waitUntilVisible(By locator) {
        super.waitUntilVisible(locator);
    }
}
