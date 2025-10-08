package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage extends BasePage {

    private final By zaraLogo = By.xpath("//a[@data-qa-action='logo-click']");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By manProductsList = By.cssSelector("ul.product-grid__product-list li");

    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");
    private final By manNavigationButton = By.xpath("//a[contains(@data-categoryid,'1885841')]");
    private final By manViewAllButton = By.xpath("//li[contains(@data-categoryid,'2431932')]");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public ManAllCatalogPage openManCatalog() {
        find(navigationButton).click();
        find(manNavigationButton).click();
        find(manViewAllButton).click();
        waitUntilVisible(manProductsList);
        return new ManAllCatalogPage(driver);
    }

    public WebElement find(By locator) {
        return super.find(locator);
    }
    public void clickLogo() {
        click(zaraLogo);
    }
    public void click(By locator) {
        super.click(locator);
    }

    public String getNameOfProduct() {
        return getText(By.cssSelector(".product-detail-info__header-name"));
    }

    public String getText(By locator) {
        return super.getText(locator);
    }

    public HomePage returnHomePage() {
        clickLogo();
        return new HomePage(driver);
    }
}
