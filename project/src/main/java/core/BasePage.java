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
        /* New way to wait is way better than using Thread.sleep function; In this approach it waits
        until the action is possible, if it will trigger in 2. second that it will return it at that time
        rather than consuming whole 10 seconds.*/
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //        driver.findElements(locator);
    protected List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    protected void type(By locator, String text) {
        WebElement el = find(locator);
        el.clear();
        el.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(el, "value"));
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

    /*
        Checks: The element exists in the DOM, regardless of whether it’s visible. */

    protected boolean isPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isEmpty(By locator) {
        WebElement el = find(locator);
        String value = el.getTagName().equalsIgnoreCase("input") || el.getTagName().equalsIgnoreCase("textarea")
                ? el.getAttribute("value")
                : el.getText();
        return value == null || value.isEmpty();
    }

    protected boolean isClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    protected void refresh() {
        driver.navigate().refresh();
    }
    protected void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    protected void waitUntilCountIs(By locator, int expected) {
        wait.until(_ -> driver.findElements(locator).size() == expected);
    }
    protected void waitUntilCountChanges(By locator, int previous) {
        wait.until(_ -> driver.findElements(locator).size() != previous);
    }
    protected void waitForTitleContains(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }

    protected WebElement findInside(WebElement parent, By childLocator) {
        return wait.until(_ -> parent.findElement(childLocator));
    }

    protected String getTextInside(WebElement parent, By childLocator) {
        waitUntilVisible(childLocator);
        return findInside(parent, childLocator).getText().trim();
    }


}
