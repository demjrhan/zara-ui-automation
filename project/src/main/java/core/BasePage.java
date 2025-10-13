package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void clearTextField(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }

    protected void click(WebElement element) {
        scrollToElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void checkFrontendValidations(By locator) {
        WebElement element = findVisibility(locator);
        Boolean validNow = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", element);
        String pattern = element.getAttribute("pattern");
        String required = element.getAttribute("required");
        String minLen = element.getAttribute("minlength");
        String maxLen = element.getAttribute("maxlength");

        System.out.printf("ValidNow: %s\n", validNow);
        System.out.printf("Pattern: %s\n", pattern);
        System.out.printf("Required: %s\n", required);
        System.out.printf("MinLen: %s\n", minLen);
        System.out.printf("MaxLen: %s\n", maxLen);

    }

    protected List<WebElement> findAllPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> findAllVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement findInside(WebElement parent, By childLocator) {
        return wait.until(_ -> parent.findElement(childLocator));
    }

    protected WebElement findPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected int getCountOfElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).size();
    }

    /* This method is only going to return the visible text inside of that locator, for instance a span text existing in the DOM will not be returned.*/
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    /* If you specifically want to get some text in child element, use this method rather than getText. */
    protected String getTextInside(WebElement parent, By childLocator) {
        return findInside(parent, childLocator).getText().trim();
    }

    protected boolean isClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isEmpty(By locator) {
        WebElement el = findVisibility(locator);
        String value = el.getTagName().equalsIgnoreCase("input") || el.getTagName().equalsIgnoreCase("textarea")
                ? el.getAttribute("value")
                : el.getText();
        return value == null || value.isEmpty();
    }

    /* Checks: Element is in the DOM and visible (display != none and opacity != 0 and has size > 0).  */
    protected boolean isVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /* Checks: Element is in the DOM and invisible (display == none and opacity == 0 and has size <= 0).  */
    protected boolean isNotVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void refresh() {
        driver.navigate().refresh();
    }

    protected void scrollDown500Pixels() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
    }

    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected void scrollToBottomGradually() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo({top: document.body.scrollHeight * 0.8, behavior: 'smooth'});"
        );
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end'});", element);
    }

    protected void scrollUp500Pixels() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500)");
    }

    protected void sendEnter(By locator) {
        WebElement el = findVisibility(locator);
        el.sendKeys(Keys.ENTER);
    }

    protected void sendTab(By locator) {
        WebElement el = findVisibility(locator);
        el.sendKeys(Keys.TAB);
    }

    protected void type(By locator, String text) {
        WebElement el = findVisibility(locator);
        el.clear();
        el.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(el, "value"));
    }

    protected void waitForStaleElementLocated(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    protected void waitForTitleContains(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }

    protected void waitUntilCountChanges(By locator, int previous) {
        wait.until(_ -> driver.findElements(locator).size() != previous);
    }

    protected void waitUntilCountIs(By locator, int expected) {
        wait.until(_ -> driver.findElements(locator).size() == expected);
    }

    protected void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
