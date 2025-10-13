package tests.negative;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignUpNegativeTest extends BaseTest {

    @Test(groups = "negative")
    @Story("Email Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify email field does not accept random text.")
    @Step("Check email field validation.")
    public void checkEmailFieldValidationRejectsRandomText() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToEmailField("random-data");
        String emailError = signup.getErrorFieldOfEmail();

        Assert.assertFalse(emailError.isEmpty(),
                "Expected an error message for invalid email, but none was displayed.");
    }

    @Test(groups = "negative")
    @Story("Password Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify password field rejects an invalid/weak password.")
    @Step("Check password field validation.")
    public void checkPasswordFieldValidationRejectsWeakPassword() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToPasswordField("123");
        String passwordError = signup.getErrorFieldOfPassword();

        Assert.assertFalse(passwordError.isEmpty(),
                "Expected an error message for invalid password, but none was displayed.");
    }

    @Test(groups = "negative")
    @Story("First Name Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify first name field rejects non-alphabetic/invalid characters.")
    @Step("Check first name field validation.")
    public void checkNameFieldValidationRejectsInvalidChars() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToNameField(" ");
        String nameError = signup.getErrorFieldOfName();


        Assert.assertFalse(nameError.isEmpty(),
                "Expected an error message for invalid first name, but none was displayed.");
    }

    @Test(groups = "negative")
    @Story("Surname Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify surname field rejects non-alphabetic/invalid characters.")
    @Step("Check surname field validation.")
    public void checkSurnameFieldValidationRejectsInvalidChars() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToSurnameField(" ");
        String surnameError = signup.getErrorFieldOfSurname();

        Assert.assertFalse(surnameError.isEmpty(),
                "Expected an error message for invalid surname, but none was displayed.");
    }

    @Test(groups = "negative")
    @Story("Phone Prefix Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify phone prefix field rejects invalid/non-numeric input.")
    @Step("Check phone prefix field validation.")
    public void checkPhonePrefixFieldValidationRejectsInvalidInput() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToPhonePrefixField("abc");
        String phonePrefixError = signup.getErrorFieldOfPhonePrefix();

        Assert.assertFalse(phonePrefixError.isEmpty(),
                "Expected an error message for invalid phone prefix, but none was displayed.");
    }

    @Test(groups = "negative")
    @Story("Phone Number Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify phone number field rejects non-numeric or too-short input.")
    @Step("Check phone number field validation.")
    public void checkPhoneNumberFieldValidationRejectsInvalidInput() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToPhoneNumberField("12ab");
        String phoneNumberError = signup.getErrorFieldOfPhoneNumber();

        Assert.assertFalse(phoneNumberError.isEmpty(),
                "Expected an error message for invalid phone number, but none was displayed.");
    }


}
