package tests.positive;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static utils.CredentialsManager.getRandomEmail;

public class SignUpPositiveTest extends BaseTest {

    @DataProvider(name = "validEmails")
    public Object[][] validEmails() {
        return new Object[][]{
                {getRandomEmail("test")},
                {getRandomEmail("user")},
                {getRandomEmail("qa")}
        };
    }

    @DataProvider(name = "validPasswords")
    public Object[][] validPasswords() {
        return new Object[][]{
                {"Qa!23456"},
                {"StrongPass9#"},
                {"ZaraTest_2025!"}
        };
    }

    @DataProvider(name = "validNames")
    public Object[][] validNames() {
        return new Object[][]{
                {"Demirhan", "Yalcin"},
                {"Emma", "Cichocka"},
                {"Random", "Name"}
        };
    }

    @DataProvider(name = "validPhones")
    public Object[][] validPhones() {
        return new Object[][]{
                {"+48", "600700800"},
                {"+1", "5062345678"},
                {"+44", "7400123456"}
        };
    }


    @Test(groups = "positive")
    @Story("Email Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify email field accepts valid formats and shows no error.")
    @Step("Enter valid email and check error is empty")
    public void emailFieldAcceptsValidFormat() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        String email = getRandomEmail("ok");
        signup.writeToEmailField(email);

        Assert.assertFalse(signup.isEmailErrorFieldVisible(), "Expected no email error for a valid address.");
    }

    @Test(groups = "positive", dataProvider = "validPasswords")
    @Story("Password Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify password field accepts strong passwords and shows no error.")
    @Step("Enter valid password and check error is empty")
    public void passwordFieldAcceptsStrongPassword(String pwd) {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToPasswordField(pwd);

        Assert.assertFalse(signup.isPasswordErrorFieldVisible(), "Expected no password error for a strong password.");
    }

    @Test(groups = "positive", dataProvider = "validNames")
    @Story("First/Last Name Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify name and surname fields accept alphabetic input and show no error.")
    @Step("Enter valid first and last names; verify no errors")
    public void nameAndSurnameAcceptValidCharacters(String firstName, String lastName) {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToNameField(firstName);
        signup.writeToSurnameField(lastName);

        Assert.assertFalse(signup.isNameErrorFieldVisible(), "Expected no error for first name.");
        Assert.assertFalse(signup.isSurnameErrorFieldVisible(), "Expected no error for surname.");
    }

    @Test(groups = "positive", dataProvider = "validPhones")
    @Story("Phone Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify phone prefix and phone number accept valid numeric input and show no error.")
    @Step("Enter valid phone prefix and number; verify no errors")
    public void phoneFieldsAcceptValidNumbers(String prefix, String number) {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToPhonePrefixField(prefix);
        signup.writeToPhoneNumberField(number);

        Assert.assertFalse(signup.isPhonePrefixErrorFieldVisible(), "Expected no error for phone prefix.");
        Assert.assertFalse(signup.isPhoneNumberErrorFieldVisible(), "Expected no error for phone number.");
    }

    @Test(groups = "positive")
    @Story("Form State")
    @Severity(SeverityLevel.CRITICAL)
    @Description("With all valid fields and required consents ticked, the Create Account button becomes enabled.")
    @Step("Fill form with valid data and check CTA enabled (no submit)")
    public void createAccountButtonEnabledWithValidForm() {
        var signup = homePage.open()
                .acceptCookiesIfPresent()
                .openSignUp();

        signup.writeToEmailField(getRandomEmail("complete"));
        signup.writeToPasswordField("ValidPass9!");
        signup.writeToNameField("Jan");
        signup.writeToSurnameField("Nowak");
        signup.writeToPhonePrefixField("+48");
        signup.writeToPhoneNumberField("600700800");

        signup.tickPrivacyConsentIfPresent()
                .tickPersonalisedEmailConsentIfPresent();

        Assert.assertFalse(signup.isEmailErrorFieldVisible(), "Email should have no error.");
        Assert.assertFalse(signup.isPasswordErrorFieldVisible(), "Password should have no error.");
        Assert.assertFalse(signup.isNameErrorFieldVisible(),"First name should have no error.");
        Assert.assertFalse(signup.isSurnameErrorFieldVisible(), "Surname should have no error.");
        Assert.assertFalse(signup.isPhonePrefixErrorFieldVisible(), "Phone prefix should have no error.");
        Assert.assertFalse(signup.isPhoneNumberErrorFieldVisible(), "Phone number should have no error.");

    }


}
