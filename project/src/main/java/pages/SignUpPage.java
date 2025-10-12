package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    private final By homeRoot = By.id("I2024-HOME");
    private final By zaraLogo = By.xpath("//a[contains(@class,'layout-header-logo__link')]");
    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");

    private final By signUpTitle = By.xpath("//title[contains(text(), 'Registration')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage open() {
        driver.get("https://www.zara.com/pl/en/signup");
        waitUntilVisible(signUpTitle);
        return this;
    }

    public HomePage returnHomePage() {
        click(navigationButton);
        clickLogo();
        waitUntilVisible(homeRoot);
        return new HomePage(driver);
    }

    public void clickLogo() {
        click(zaraLogo);
    }

}
