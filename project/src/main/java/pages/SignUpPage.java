package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage extends BasePage {

    private final By homeRoot = By.id("I2024-HOME");
    private final By zaraLogo = By.xpath("//a[contains(@class,'layout-header-logo__link')]");
    private final By navigationButton = By.xpath("//button[contains(@data-qa-id,'layout-header-toggle-menu')]");

    private final By signUpTitle = By.xpath("//title[contains(text(), 'Registration')]");

    private final By emailField = By.xpath("//input[contains(@data-qa-input-qualifier,'email')]");
    private final By emailFieldErrorContainer = By.xpath("//div[contains(@data-name,'email')]//div[contains(@class,'form-input-error')]");

    private final By passwordField = By.xpath("//input[contains(@data-qa-input-qualifier,'password')]");
    private final By passwordFieldErrorContainer = By.xpath("//div[contains(@data-name,'password')]//div[contains(@class,'form-input-error')]");

    private final By nameField = By.xpath("//input[contains(@data-qa-input-qualifier,'firstName')]");
    private final By nameFieldErrorContainer = By.xpath("//div[contains(@data-name,'firstName')]//div[contains(@class,'form-input-error')]");

    private final By surnameField = By.xpath("//input[contains(@data-qa-input-qualifier,'lastName')]");
    private final By surnameFieldErrorContainer = By.xpath("//div[contains(@data-name,'lastName')]//div[contains(@class,'form-input-error')]");

    private final By phoneNumberField = By.xpath("//input[contains(@data-qa-input-qualifier,'phone.number')]");
    private final By phoneNumberFieldErrorContainer = By.xpath("//div[contains(@data-name,'autocompletePhone')]//div[contains(@class,'form-input-error')]");

    private final By phonePrefix = By.xpath("//input[contains(@data-qa-input-qualifier,'phone.prefix')]");
    private final By phonePrefixFieldErrorContainer = By.xpath("//div[contains(@data-name,'autocompletePhone')]//div[contains(@class,'form-input-error')]");


    private final By personalisedEmailConsent = By.xpath("//div[contains(@data-name,'newsletterCheck')]//span");
    private final By privacyConsent = By.xpath("//div[contains(@data-name,'privacyCheck')]//span");


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


    public void clickEmailField() {
        click(emailField);
    }

    public void writeToEmailField(String email) {
        click(emailField);
        type(emailField, email);
        sendTab(emailField);
    }

    public String getErrorFieldOfEmail() {
        return getText(emailFieldErrorContainer);
    }

    public boolean isEmailErrorFieldVisible() {
        return isVisible(emailFieldErrorContainer);
    }

    public void clickPasswordField() {
        click(passwordField);
    }

    public void writeToPasswordField(String password) {
        click(passwordField);
        type(passwordField, password);
        sendTab(passwordField);
    }

    public String getErrorFieldOfPassword() {
        return getText(passwordFieldErrorContainer);
    }

    public boolean isPasswordErrorFieldVisible() {
        return isVisible(passwordFieldErrorContainer);
    }

    public void clickNameField() {
        click(nameField);
    }

    public void writeToNameField(String name) {
        click(nameField);
        type(nameField, name);
        sendTab(nameField);
    }

    public String getErrorFieldOfName() {
        return getText(nameFieldErrorContainer);
    }

    public boolean isNameErrorFieldVisible() {
        return isVisible(nameFieldErrorContainer);
    }

    public void clickSurnameField() {
        click(surnameField);
    }

    public void writeToSurnameField(String surname) {
        click(surnameField);
        type(surnameField, surname);
        sendTab(surnameField);
    }

    public String getErrorFieldOfSurname() {
        return getText(surnameFieldErrorContainer);
    }

    public boolean isSurnameErrorFieldVisible() {
        return isVisible(surnameFieldErrorContainer);
    }

    public void clickPhonePrefixField() {
        click(phonePrefix);
    }

    public void writeToPhonePrefixField(String prefix) {
        click(phonePrefix);
        type(phonePrefix, prefix);
        sendTab(phonePrefix);
    }

    public String getErrorFieldOfPhonePrefix() {
        return getText(phonePrefixFieldErrorContainer);
    }

    public boolean isPhonePrefixErrorFieldVisible() {
        return isVisible(phonePrefixFieldErrorContainer);
    }


    public void clickPhoneNumberField() {
        click(phoneNumberField);
    }

    public void writeToPhoneNumberField(String number) {
        click(phoneNumberField);
        type(phoneNumberField, number);
        sendTab(phoneNumberField);
    }

    public String getErrorFieldOfPhoneNumber() {
        return getText(phoneNumberFieldErrorContainer);
    }

    public boolean isPhoneNumberErrorFieldVisible() {
        return isVisible(phoneNumberFieldErrorContainer);
    }

    public SignUpPage tickPrivacyConsentIfPresent() {
        if (isPresent(privacyConsent)) {
            WebElement el = findVisibility(privacyConsent);
            if (!el.isSelected()) el.click();
        }
        return this;
    }

    public SignUpPage tickPersonalisedEmailConsentIfPresent() {
        if (isPresent(personalisedEmailConsent)) {
            WebElement el = findVisibility(personalisedEmailConsent);
            if (!el.isSelected()) el.click();
        }
        return this;
    }
}
