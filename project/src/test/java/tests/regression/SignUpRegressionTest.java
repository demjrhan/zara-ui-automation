package tests.regression;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignUpRegressionTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Email Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify email field does not accept random text.")
    @Step("Check email field validation.")
    public void checkSocialFooterCountOnLoadHomePage(){
        var signup = homePage.open().acceptCookiesIfPresent().openSignUp();
    }
}
