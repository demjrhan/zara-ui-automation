package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductDetailPage extends BasePage {

    private final By zaraLogo = By.xpath("//a[@data-qa-action='logo-click']");
    private final By manProductsList = By.cssSelector("ul.product-grid__product-list li");

    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");
    private final By manNavigationButton = By.xpath("//a[contains(@data-categoryid,'1885841')]");
    private final By manViewAllButton = By.xpath("//li[contains(@data-categoryid,'2431932')]");

    private final By productsList = By.cssSelector("ul.product-grid__product-list li");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public ManAllCatalogPage openManCatalog() {
        findVisibility(navigationButton).click();
        findVisibility(manNavigationButton).click();
        findVisibility(manViewAllButton).click();
        waitUntilVisible(manProductsList);
        return new ManAllCatalogPage(driver);
    }

    public void clickLogo() {
        click(zaraLogo);
    }

    public String getNameOfProduct() {
        return getText(By.cssSelector(".product-detail-info__header-name"));
    }

    public HomePage returnHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    public List<WebElement> getYouMayBeInterestedInProducts() {
        return findAllVisibility(productsList);
    }
    public int getYouMayBeInterestedInProductsCount() {
        return getCountOfElements(manProductsList);
    }
}
