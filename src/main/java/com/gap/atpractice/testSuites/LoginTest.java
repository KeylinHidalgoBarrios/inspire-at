package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageObject.ForgotPasswordPage;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.utils.TakeScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by auto on 06/04/17.
 */
public class LoginTest extends TestBase{
    protected LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    private void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    @Test(groups = {"smoke", "test001"})
    @Parameters({"userName", "password"})
    public void loginTest(String userName, String password){
        try {

            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);

            Assert.assertTrue(homePage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Home page cannot be displayed");

            //Taking the screenshot after Welcome page loads
            TakeScreenshot.takeScreenshot(driver, "./src/main/resources/screenshots/screenshot2.png", "png");

            //Using javascript executor to validate the page is ready
            JavascriptExecutor js = (JavascriptExecutor)driver;
            Assert.assertTrue(js.executeScript("return document.readyState").equals("complete"), "JavascriptExecutor document not loaded");

            quitBrowser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(groups = {"resetPassword", "test001"})
    public void goToForgotPasswordPage(){
        try {
            goToLoginPage();

            ForgotPasswordPage newPasswordPage = loginPage.goToNewPasswordPage();

            Assert.assertTrue(newPasswordPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Reset Password Page can not be displayed");

            //Taking the screenshot after Forgot Password page loads
            TakeScreenshot.takeScreenshot(driver, "./src/main/resources/screenshots/ForgotPassword.png", "png");

            quitBrowser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
