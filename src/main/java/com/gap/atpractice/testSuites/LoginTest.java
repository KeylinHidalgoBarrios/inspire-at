package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageObject.ForgotPasswordPage;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.selenium.SeleniumBase;
import com.gap.atpractice.utils.TakeScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by auto on 06/04/17.
 */
public class LoginTest {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod(groups = {"smoke", "resetPassword", "test001"})
    @Parameters({"browser"})
    private void initSetup(String browser){
        SeleniumBase seleniumBase = new SeleniumBase();
        driver = seleniumBase.setup(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * General method to go to Login Page
     */
    private  void goToLoginPage(){
        loginPage = new LoginPage(driver).get();

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

            driver.quit();
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

            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
