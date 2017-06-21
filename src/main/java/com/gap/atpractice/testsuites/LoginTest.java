package com.gap.atpractice.testsuites;

import com.gap.atpractice.common.LoginTestCommon;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.testngdataprovider.DataProviderTest;
import com.gap.atpractice.pageobject.ForgotPasswordPage;
import com.gap.atpractice.pageobject.HomePage;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.utils.TakeScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by auto on 06/04/17.
 */
@Test(groups = "regression")
public class LoginTest extends TestBase {
    LoginTestCommon loginTestCommon;

    public LoginTest(){
        loginTestCommon = new LoginTestCommon();
    }
    /**
     * Test login process
     * @param email User credentials
     * @param password Password credentials
     */
    @Parameters({"email", "password"})
    public void loginSuccessfulTest(String email, String password){
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        //Taking the screenshot after Welcome page loads
        new TakeScreenshot().takeScreenshot(getDriver(),"./src/main/resources/screenshots/screenshot2.png", "png");

        //Using javascript executor to validate the page is ready
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        Assert.assertTrue(js.executeScript("return document.readyState").equals("complete"), "JavascriptExecutor document not loaded");
    }

    /**
     * Test login process to test successful and failed logins
     */
    @Test(groups = {"testngDataProvider"}, dataProvider = "dpTestLocal", dataProviderClass = DataProviderTest.class)
    public void loginTestLocal(String email, String password){
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);

        System.out.println("Local Data Provider");
        System.out.println(String.format("Username: %s    Password: %s", email, password));
    }

    /**
     * Test login process to test successful and failed logins
     */
    @Test(groups = {"testngDataProvider"}, dataProvider = "dpTestJson", dataProviderClass = DataProviderTest.class)
    public void loginTestJson(String email, String password){
        loginTestCommon.login(getDriver(), email, password);

        System.out.println("JSON Data Provider");
        System.out.println(String.format("Username: %s    Password: %s", email, password));
    }

    /**
     * Go to Forgot your password page
     */
    @Test(groups = "resetPassword")
    public void goToForgotPasswordPage(){
        LoginPage loginPage = (LoginPage) new LoginPage(getDriver()).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");

        ForgotPasswordPage forgotPasswordPage = loginPage.goToNewPasswordPage();

        Assert.assertTrue(forgotPasswordPage.isPageLoaded(), "Reset Password Page can not be displayed");
    }
}
