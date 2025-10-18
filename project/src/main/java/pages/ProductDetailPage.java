package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailPage extends BasePage {

    private final By footer = By.cssSelector("footer.layout-footer");
    private final By homeRoot = By.id("I2024-HOME");
    private final By manNavigationButton = By.xpath("//a[contains(@data-categoryid,'1885841')]");
    private final By manViewAllButton = By.xpath("//li[contains(@data-categoryid,'2431932')]");
    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");
    private final By productTitle = By.cssSelector(".product-detail-info__header-name");
    private final By productsList = By.cssSelector("ul.product-grid__product-list li");
    private final By socialFooter = By.cssSelector("ul#homeSocialFooter li");
    private final By youMayLikeSection = By.cssSelector("div.product-cross-selling-grid");
    private final By zaraLogo = By.xpath("//a[contains(@class,'layout-header-logo__link')]");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean atHomePage() {
        return isVisible(homeRoot);
    }

    public void clickLogo() {
        click(zaraLogo);
    }

    public ProductDetailPage clickInterestedProductByIndex(int index) {
        var product = getInterestedProductByIndex(index);
        click(product);
        waitUntilVisible(navigationButton);
        return new ProductDetailPage(driver);
    }

    public WebElement getFirstInterestedProduct() {
        return findAllVisibility(productsList).getFirst();
    }

    public WebElement getInterestedProductByIndex(int index) {
        return findAllVisibility(productsList).get(index);
    }

    public int getInterestedProductCount() {
        findVisibility(productsList);
        return getCountOfElements(productsList);
    }

    public List<WebElement> getInterestedProducts() {
        return findAllVisibility(productsList);
    }

    public String getProductTitleOfInterestedByElement(WebElement element) {
        return getTextInside(element, By.cssSelector("h3"));
    }

    public String getProductTitleOfInterestedByIndex(int index) {
        var cards = findAllVisibility(productsList);
        return getTextInside(cards.get(index), By.cssSelector("h3"));
    }

    public int getSocialFooterLinkCount() {
        return findAllPresence(socialFooter).size();
    }

    public List<String> getSocialFooterLinkNames() {
        var links = findAllPresence(socialFooter);
        var names = new ArrayList<String>();
        links.forEach(link -> {
            var name = getTextInside(link, By.cssSelector("a"));
            if (!name.isEmpty()) names.add(name);
        });
        return names;
    }

    public String getTitleOfProduct() {
        return getText(productTitle);
    }

    public ManAllCatalogPage openManCatalog() {
        findVisibility(navigationButton).click();
        findVisibility(manNavigationButton).click();
        findVisibility(manViewAllButton).click();
        waitUntilVisible(productsList);
        return new ManAllCatalogPage(driver);
    }

    public void returnHomePage() {
        click(navigationButton);
        clickLogo();
        waitUntilVisible(homeRoot);
        new HomePage(driver);
    }

    public void scrollDownToFooterSmooth() {
        scrollToElementSmooth(findPresence(footer));
    }

    public void scrollDownToSocialFooterSmooth() {
        scrollToElementSmooth(findPresence(socialFooter));
    }

    public void scrollToInterestedProduct() {
        scrollToBottomGradually();
        waitUntilVisible(youMayLikeSection);
    }
}
